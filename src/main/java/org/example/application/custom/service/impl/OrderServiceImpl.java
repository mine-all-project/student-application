package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.entity.Order;
import org.example.application.custom.form.OrderForm;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public OrderServiceImpl(OrderDAO orderDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Order> getAll(HttpServletRequest request) {
        lastTimeOrder();
        checkOrderAuth(request, jwtConfigure, userDAO);
        return orderDAO.getAll();
    }

    void lastTimeOrder() {
        LocalDate now = LocalDate.now();
        List<Order> list = orderDAO.getAll().stream().peek(e -> {
            if (e.getStatus() == DIC.CHECK_WAIT) {
                if (null != e.getLastTime()) {
                    if (e.getLastTime().isBefore(now)) {
                        e.setStatus(DIC.CHECK_FAIL);
                    }
                }
            }
        }).collect(Collectors.toList());
        orderDAO.saveAll(list);
    }

    @Override
    public List<Order> search(HttpServletRequest request, String keywords) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        return orderDAO.search(keywords);
    }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Order> searchDate(HttpServletRequest request, String beginTimeStr, String endTimeStr) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        LocalDateTime beginTime = LocalDateTime.parse(beginTimeStr, dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, dateTimeFormatter);
        return orderDAO.searchDate(beginTime, endTime);
    }

    @Override
    public Order save(HttpServletRequest request, OrderForm form) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        Order entity = form.toEntity();
        if (StringUtils.isEmpty(entity.getNo())) {
            entity.setNo(String.valueOf(System.currentTimeMillis()));
        }
        return orderDAO.save(entity);
    }


    @Override
    public void deleteById(HttpServletRequest request, String id) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        orderDAO.deleteById(id);
    }

    @Override
    public void allPass(HttpServletRequest request) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        List<Order> list = getAll(request).stream().peek(e -> {
            e.setStatus(DIC.CHECK_PASS);
        }).collect(Collectors.toList());
        orderDAO.saveAll(list);
    }

    @Override
    public void checkPass(HttpServletRequest request, String id) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        orderDAO.updateStatusById(id, DIC.CHECK_PASS);
    }

    @Override
    public void checkFail(HttpServletRequest request, String id) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        orderDAO.updateStatusById(id, DIC.CHECK_FAIL);
    }
}

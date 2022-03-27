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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public List<Order> search(String keywords) {
        return orderDAO.search(keywords);
    }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Order> searchDate(String beginTimeStr, String endTimeStr) {
        LocalDateTime beginTime = LocalDateTime.parse(beginTimeStr, dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, dateTimeFormatter);
        return orderDAO.searchDate(beginTime, endTime);
    }

    @Override
    public Order save(HttpServletRequest request, OrderForm form) {
        Order entity = form.toEntity();
        if (StringUtils.isEmpty(entity.getNo())) {
            entity.setNo(String.valueOf(System.currentTimeMillis()));
        }
        return orderDAO.save(entity);
    }


    @Override
    public void deleteById(String id) {
        orderDAO.deleteById(id);
    }

    @Override
    public void checkPass(String id) {
        orderDAO.updateStatusById(id, DIC.CHECK_PASS);
    }

    @Override
    public void checkFail(String id) {
        orderDAO.updateStatusById(id, DIC.CHECK_FAIL);
    }
}

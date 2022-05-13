package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.dao.PersonDAO;
import org.example.application.custom.entity.Order;
import org.example.application.custom.entity.Person;
import org.example.application.custom.form.MoneyForm;
import org.example.application.custom.form.OrderForm;
import org.example.application.custom.service.OrderService;
import org.example.application.custom.service.PersonService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final PersonService personService;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final PersonDAO personDAO;
    private final JwtConfigure jwtConfigure;

    public OrderServiceImpl(OrderDAO orderDAO, PersonService personService, UserDAO userDAO, PersonDAO personDAO, JwtConfigure jwtConfigure) {
        this.orderDAO = orderDAO;
        this.personService = personService;
        this.userDAO = userDAO;
        this.personDAO = personDAO;
        this.jwtConfigure = jwtConfigure;
    }

    List<Order> filterByUser(HttpServletRequest request, List<Order> source) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return source.stream().filter(e -> {
            SysUser createBy = e.getCreateBy();
            if (null != createBy) {
                return sysUser.getId().equals(createBy.getId());
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll(HttpServletRequest request) {
        lastTimeOrder();
        checkOrderAuth(request, jwtConfigure, userDAO);
        return filterByUser(request, orderDAO.getAll());
    }

    private boolean autoReduce(Order order) {
        MoneyForm form = new MoneyForm();
        form.setId(order.getPerson().getId());
        form.setMoneyReduce(order.getPrice());
        Person person = personDAO.getById(form.getId());
        if (person == null) {
            return false;
        }
        BigDecimal money = person.getMoney();
        BigDecimal decimal = money.subtract(form.getMoneyReduce());
        if (decimal.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        person.setMoney(decimal);
        personDAO.save(person);
        return true;
    }

    private void lastTimeOrder() {
        LocalDate now = LocalDate.now();
        List<Order> list = orderDAO.getAll().stream().peek(e -> {
            if (e.getStatus() == DIC.CHECK_WAIT) {
                if (null != e.getLastTime()) {
                    if (e.getLastTime().isBefore(now)) {
                        boolean isAutoReduce = autoReduce(e);
                        if (!isAutoReduce) {
                            e.setStatus(DIC.CHECK_FAIL);
                        } else {
                            e.setStatus(DIC.CHECK_PASS);
                        }
                    }
                }
            }
        }).collect(Collectors.toList());
        orderDAO.saveAll(list);
    }

    @Override
    public List<Order> search(HttpServletRequest request, String name) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        return filterByUser(request, orderDAO.searchByUser(name));
    }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Order> searchDate(HttpServletRequest request, String beginTimeStr, String endTimeStr) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        LocalDateTime beginTime = LocalDateTime.parse(beginTimeStr, dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, dateTimeFormatter);
        return filterByUser(request, orderDAO.searchDate(beginTime, endTime));
    }

    @Override
    public Order save(HttpServletRequest request, OrderForm form) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        checkOrderAuth(request, jwtConfigure, userDAO);
        Order entity = form.toEntity();
        entity.setCreateBy(sysUser);
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
        Order order = orderDAO.findById(id);
        MoneyForm form = new MoneyForm();
        form.setId(order.getPerson().getId());
        form.setMoneyReduce(order.getPrice());
        personService.moneyReduce(request, form);
        orderDAO.updateStatusById(id, DIC.CHECK_PASS);
    }

    @Override
    public void checkFail(HttpServletRequest request, String id) {
        checkOrderAuth(request, jwtConfigure, userDAO);
        orderDAO.updateStatusById(id, DIC.CHECK_FAIL);
    }
}

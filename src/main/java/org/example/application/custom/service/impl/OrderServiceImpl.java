package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.ApplicationException;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.entity.Order;
import org.example.application.custom.form.OrderForm;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public Order save(HttpServletRequest request, OrderForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Order entity = form.toEntity();
        entity.setStatus(DIC.CHECK_WAIT);
        return orderDAO.save(entity);
    }


    @Override
    public void deleteById(String id) {
        orderDAO.deleteById(id);
    }

    @Override
    public void checkPass(OrderForm form) {
        throw new ApplicationException("暂未实现");
//        orderDAO.updateStatusById(form.getId(), DIC.CHECK_PASS, form.getNote());
    }

    @Override
    public void checkFail(OrderForm form) {
        throw new ApplicationException("暂未实现");
//        orderDAO.updateStatusById(form.getId(), DIC.CHECK_FAIL, form.getNote());
    }
}

package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.entity.Order;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final GoodsService goodsService;

    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public OrderServiceImpl(OrderDAO orderDAO, GoodsService goodsService, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.orderDAO = orderDAO;
        this.goodsService = goodsService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public Order saveOrder(HttpServletRequest request, Order entity) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        entity.setUser(user);
        return orderDAO.saveOrder(entity);
    }

    @Override
    public List<Order> getOrderList(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return orderDAO.selectByUserId(user);
    }

    @Override
    public List<Order> getAllOrder(HttpServletRequest request) {
        return orderDAO.selectAll();
    }

    @Override
    public Order getOrderById(String id) {
        return orderDAO.selectById(id);
    }

    @Override
    public void deleteOrder(String id) {
        orderDAO.deleteOrder(id);
    }
}

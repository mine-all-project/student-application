package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.entity.Order;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.OrderService;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final GoodsService goodsService;
    private final StoreCarService storeCarService;

    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public OrderServiceImpl(OrderDAO orderDAO, GoodsService goodsService, StoreCarService storeCarService, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.orderDAO = orderDAO;
        this.goodsService = goodsService;
        this.storeCarService = storeCarService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public void addOrder(HttpServletRequest request) {
        String userId = getUserInfo(request, jwtConfigure, userDAO, isDebug).getId();
        List<Goods> storeCar = storeCarService.getStoreCar(request);
        BigDecimal prices = new BigDecimal(0);
        StringBuilder goodsIds = new StringBuilder();
        Order order = new Order();
        for (Goods goods : storeCar) {
            prices = prices.add(goods.getPrice());
            goodsIds.append(goods.getId());
        }
        order.setId(UUID.randomUUID().toString());
        order.setGoodsIds(goodsIds.toString());
        order.setPrice(prices);
        order.setUserId(userId);
        order.setCreateTime(LocalDateTime.now());
        orderDAO.addOrder(order);
        storeCarService.cleanStoreCar(request);
    }

    @Override
    public List<Order> getOrder(HttpServletRequest request) {
        String userId = getUserInfo(request, jwtConfigure, userDAO, isDebug).getId();
        return orderDAO.selectByUserId(userId);
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

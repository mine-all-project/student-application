package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.entity.Order;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.OrderService;
import org.example.application.custom.service.StoreCarService;
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
    public Order saveOrder(HttpServletRequest request, Order entity) {
//        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
//        entity.setUser(user);
//        StoreCar storeCar = storeCarService.getStoreCar(request);
//        List<StoreCar.StoreCarItem> source = storeCar.getGoods();
//        List<Goods> goodsList = new ArrayList<>();
//        BigDecimal price = new BigDecimal(0);
//        for (StoreCar.StoreCarItem goods : source) {
//            goodsList.add(goods.getGoods());
//            price = price.add(goods.getGoods().getPrice());
//        }
//        Collections.copy(goodsList, source);
//        entity.setGoods(goodsList);
//        entity.setPrice(price);
//        return orderDAO.saveOrder(entity);
        return null;
    }

    @Override
    public Order saveOrderNote(HttpServletRequest request, String id, String note) {
        Order entity = orderDAO.selectById(id);
        entity.setNote(note);
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

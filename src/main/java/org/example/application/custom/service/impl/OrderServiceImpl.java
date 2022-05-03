package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.dao.StoreCarItemDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.entity.Order;
import org.example.application.custom.entity.StoreCar;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.OrderService;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final GoodsService goodsService;
    private final StoreCarService storeCarService;
    private final StoreCarItemDAO storeCarItemDAO;

    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public OrderServiceImpl(OrderDAO orderDAO, GoodsService goodsService,
                            StoreCarService storeCarService, StoreCarItemDAO storeCarItemDAO,
                            UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.orderDAO = orderDAO;
        this.goodsService = goodsService;
        this.storeCarService = storeCarService;
        this.storeCarItemDAO = storeCarItemDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public Order saveOrder(HttpServletRequest request, Order entity) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        entity.setUser(user);
//        StoreCar storeCar = storeCarService.getStoreCar(request);
//        List<StoreCar.StoreCarItem> storeCarItems = storeCar.getItem();
//        storeCarItems.removeAll(entity.getGoods());
        List<StoreCar.StoreCarItem> goodsList = new ArrayList<>(entity.getGoods());
        BigDecimal price = new BigDecimal(0);
        for (StoreCar.StoreCarItem item : goodsList) {
            Goods goods = item.getGoods();
            BigDecimal decimal = goods.getPrice().multiply(new BigDecimal(item.getCountNum()));
            price = price.add(decimal);
        }
//        goodsList = storeCarItemDAO.saveAll(goodsList);
        entity.setGoods(goodsList);
        entity.setPrice(price);
        System.err.println(entity);
        return orderDAO.saveOrder(entity);
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

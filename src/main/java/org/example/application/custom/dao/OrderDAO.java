package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.OrderMapper;
import org.example.application.custom.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDAO extends BaseDAO {
    private final OrderMapper orderMapper;

    public OrderDAO(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    public void addOrder(Order type) {
        type.setId(UUID.randomUUID().toString());
        orderMapper.insert(type);
    }


    public List<Order> selectByUserId(String userId) {
        return orderMapper.selectByUserId(userId);
    }
}

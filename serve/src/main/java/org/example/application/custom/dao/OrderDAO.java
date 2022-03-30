package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.OrderRepository;
import org.example.application.custom.entity.Order;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO extends BaseDAO {
    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order entity) {
        return orderRepository.saveAndFlush(entity);
    }

    public List<Order> selectByUserId(SysUser user) {
        return orderRepository.findByUser(user, desByCreateTime);
    }

    public List<Order> selectAll() {
        return orderRepository.findAll();
    }

    public Order selectById(String id) {
        return orderRepository.findById(id).orElse(new Order());
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}

package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.OrderRepository;
import org.example.application.custom.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO extends BaseDAO {
    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll(desByCreateTime);
    }

    public List<Order> search(String keywords) {
        return orderRepository.findByKeywordLike(keywords, desByCreateTime);
    }

    public Order save(Order entity) {
        return orderRepository.saveAndFlush(entity);
    }


    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

}

package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.OrderRepository;
import org.example.application.custom.entity.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        return orderRepository.findByKeywordContains(keywords, desByCreateTime);
    }

    public Order save(Order entity) {
        return orderRepository.saveAndFlush(entity);
    }


    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateStatusById(String id, int status) {
        orderRepository.updateStatusById(id,status);
    }

    public List<Order> searchDate(LocalDateTime beginTime, LocalDateTime endTime) {
        return orderRepository.findByCreateTimeBetween(beginTime, endTime,desByCreateTime);
    }

    public List<Order> saveAll(List<Order> list) {
        return orderRepository.saveAll(list);
    }

    public Order findById(String id) {
        return orderRepository.findById(id).orElse(null);
    }
}

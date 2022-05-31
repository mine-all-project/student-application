package org.example.application.common.config;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.custom.dao.OrderDAO;
import org.example.application.custom.dao.jpa.OrderItemRepository;
import org.example.application.custom.entity.Order;
import org.example.application.custom.entity.OrderItem;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration

@Slf4j
public class TaskConfigure {
    private final OrderDAO orderDAO;
    private final OrderItemRepository orderItemRepository;

    public TaskConfigure(OrderDAO orderDAO, OrderItemRepository orderItemRepository) {
        this.orderDAO = orderDAO;
        this.orderItemRepository = orderItemRepository;
    }

//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(cron = "0/30 * * * * ?")
    public void refuseOrder() {
        log.info("开始刷新月账单");
        List<Order> list = orderDAO.getAll().stream().filter(e -> {
            Duration start = Duration.between(LocalDateTime.now().minusMonths(1L), e.getCreateTime());
            Duration end = Duration.between(e.getCreateTime(), LocalDateTime.now());
            return e.getType() == 1 && start.toDays() >= 0 && end.toDays() <= 31;
        }).map(e -> {
            Order order = new Order();
            BeanUtils.copyProperties(e, order);
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());
            order.setStatus(DIC.CHECK_WAIT);
            order.setNo(String.valueOf(System.currentTimeMillis()));
            order.setId(null);
            order.setLastTime(e.getLastTime().plusMonths(1L));
            List<OrderItem> children = e.getChildrenOrder().stream().peek(r -> r.setId(null)).collect(Collectors.toList());
            children = orderItemRepository.saveAll(children);
            order.setChildrenOrder(children);
            return order;
        }).collect(Collectors.toList());
        orderDAO.saveAll(list);
        log.info("月账单刷新结束");
    }
}

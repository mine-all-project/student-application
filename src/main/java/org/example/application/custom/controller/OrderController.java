package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Order;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController extends BaseController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseDTO addOrder(HttpServletRequest request) {
        log.info("收到请求->添加订单");
        orderService.addOrder(request);
        log.info("返回结果->添加订单结束");
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/get")
    public ResponseDTO getOrder(HttpServletRequest request) {
        log.info("收到请求->获取订单");
        List<Order> list = orderService.getOrder(request);
        log.info("返回结果->获取订单结束");
        return ResponseDTO.returnSuccess();
    }
}

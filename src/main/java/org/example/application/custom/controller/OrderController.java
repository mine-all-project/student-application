package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.custom.entity.Order;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/save")
    public ResponseDTO saveOrder(HttpServletRequest request,@RequestBody Order form) {
        validator(form, Groups.IsNotNull.class);
        log.info("收到请求->保存订单:[{}]",form);
        Order entity = orderService.saveOrder(request,form);
        log.info("返回结果->保存订单结束:[{}]",entity);
        return ResponseDTO.returnSuccess(entity);
    }
    @GetMapping("/saveNote/{id}")
    public ResponseDTO saveOrderNote(HttpServletRequest request, String note, @PathVariable String id) {
        log.info("收到请求->保存订单备注:[{}]",note);
        Order entity = orderService.saveOrderNote(request,id,note);
        log.info("返回结果->保存订单备注结束:[{}]",entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteOrder(@PathVariable String id) {
        log.info("收到请求->删除订单,id:[{}]", id);
        orderService.deleteOrder(id);
        log.info("返回结果->删除订单结束");
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/get/list")
    public ResponseDTO getOrderList(HttpServletRequest request) {
        log.info("收到请求->获取订单列表");
        List<Order> list = orderService.getOrderList(request);
        System.err.println(list);
        log.info("返回结果->获取订单列表结束:[{}]",list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/get/id/{id}")
    public ResponseDTO getOrderById(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->获取订单,id:[{}]", id);
        Order entity = orderService.getOrderById(id);
        log.info("返回结果->获取订单结束,[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/getAll")
    public ResponseDTO getAllOrder(HttpServletRequest request) {
        log.info("收到请求->获取所有订单");
        List<Order> list = orderService.getAllOrder(request);
        log.info("返回结果->获取订单结束");
        return ResponseDTO.returnSuccess(list);
    }
}

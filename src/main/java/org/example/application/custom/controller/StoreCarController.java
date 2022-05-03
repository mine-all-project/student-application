package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Order;
import org.example.application.custom.entity.StoreCar;
import org.example.application.custom.service.OrderService;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/storeCar")
public class StoreCarController extends BaseController {
    private final OrderService orderService;
    private final StoreCarService storeCarService;

    public StoreCarController(OrderService orderService, StoreCarService storeCarService) {
        this.orderService = orderService;
        this.storeCarService = storeCarService;
    }
    @GetMapping("/get")
    public ResponseDTO getStoreCar(HttpServletRequest request) {
        log.info("收到请求->获取购物车");
        StoreCar entity = storeCarService.getStoreCar(request);
        log.info("返回结果->获取购物车结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/add/{id}")
    public ResponseDTO addStoreCar(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->加入购物车:[{}]", id);
        storeCarService.addStoreCar(request, id);
        log.info("返回结果->加入购物车结束:[{}]", id);
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/change")
    public ResponseDTO changeStoreCar(HttpServletRequest request,@RequestBody StoreCar.StoreCarItem entity) {
        log.info("收到请求->修改购物车商品数量:[{}]", entity);
        storeCarService.changeStoreCar(request, entity);
        log.info("返回结果->修改购物车结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/remove/{id}")
    public ResponseDTO removeStoreCar(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->删除购物车:[{}]", id);
        storeCarService.removeStoreCar(request, id);
        log.info("返回结果->删除购物车结束");
        return ResponseDTO.returnSuccess();
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
        log.info("收到请求->获取订单");
        List<Order> list = orderService.getOrderList(request);
        log.info("返回结果->获取订单结束");
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

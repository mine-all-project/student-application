package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Order;
import org.example.application.custom.form.OrderForm;
import org.example.application.custom.service.OrderService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/order/")
public class OrderController extends BaseController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ResponseDTO getList(HttpServletRequest request) {
        log.info("收到请求->获取账单列表");
        List<Order> list = orderService.getAll(request);
        log.info("返回结果->获取账单列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search/{keywords}")
    public ResponseDTO search(HttpServletRequest request,@PathVariable String keywords) {
        log.info("收到请求->搜索账单列表,keywords:[{}]", keywords);
        List<Order> list = orderService.search(request,keywords);
        log.info("返回结果->搜索账单列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/searchDate/{beginTime}/{endTime}")
    public ResponseDTO searchDate(HttpServletRequest request,@PathVariable String beginTime, @PathVariable String endTime) {
        log.info("收到请求->搜索账单列表,time:[{} - {}]", beginTime, endTime);
        List<Order> list = orderService.searchDate(request,beginTime, endTime);
        log.info("返回结果->搜索账单列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody OrderForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->添加账单:[{}]", form);
        Order entity = orderService.save(request, form);
        log.info("返回结果->添加账单结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(HttpServletRequest request,@PathVariable String id) {
        log.info("收到请求->删除账单,id:[{}]", id);
        orderService.deleteById(request,id);
        log.info("返回结果->删除账单结束:");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/allPass")
    public ResponseDTO allPass(HttpServletRequest request) {
        log.info("收到请求->一键缴费");
        orderService.allPass(request);
        log.info("返回结果->一键缴费结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass/{id}")
    public ResponseDTO checkPass(HttpServletRequest request,@PathVariable String id) {
        log.info("收到请求->账单缴费,id:[{}]", id);
        orderService.checkPass(request,id);
        log.info("返回结果->账单缴费结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkFail/{id}")
    public ResponseDTO checkFail(HttpServletRequest request,@PathVariable String id) {
        log.info("收到请求->账单逾期,id:[{}]", id);
        orderService.checkFail(request,id);
        log.info("返回结果->账单逾期结束");
        return ResponseDTO.returnSuccess();
    }
}

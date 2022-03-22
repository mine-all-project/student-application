package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/storeCar")
public class StoreCarController extends BaseController {
    private final StoreCarService storeCarService;

    public StoreCarController(StoreCarService storeCarService) {
        this.storeCarService = storeCarService;
    }

    @PostMapping("/add/{goodsId}")
    public ResponseDTO addStoreCar(HttpServletRequest request, @PathVariable String goodsId) {
        log.info("收到请求->加入购物车:[{}]",goodsId);
        storeCarService.addStoreCar(request,goodsId);
        log.info("返回结果->加入购物车结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/delete/{goodsId}")
    public ResponseDTO deleteStoreCar(HttpServletRequest request, @PathVariable String goodsId) {
        log.info("收到请求->移出购物车:[{}]",goodsId);
        storeCarService.deleteStoreCar(request,goodsId);
        log.info("返回结果->移出购物车结束");
        return ResponseDTO.returnSuccess();
    }
}

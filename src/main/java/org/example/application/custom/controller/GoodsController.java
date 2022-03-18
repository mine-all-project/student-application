package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.GoodsService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping("list")
    public ResponseDTO getList() {
        log.info("收到请求->获取商品列表");
        List<Goods> list = goodsService.getAll();
        log.info("返回结果->获取商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
}

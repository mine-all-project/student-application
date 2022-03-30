package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.GoodsService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/list")
    @JwtIgnore
    public ResponseDTO getList() {
        log.info("收到请求->获取商品列表");
        List<Goods> list = goodsService.getAll();
        log.info("返回结果->获取商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveGoods(@RequestBody Goods form) {
        log.info("收到请求->保存商品:[{}]", form);
        Goods entity = goodsService.saveGoods(form);
        log.info("返回结果->保存商品结束:[{}]", entity);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/search")
    public ResponseDTO searchGoods() {
        return getList();
    }

    @GetMapping("/search/{keyword}")
    public ResponseDTO searchGoods(@PathVariable String keyword) {
        log.info("收到请求->搜索商品,keyword:[{}]", keyword);
        List<Goods> list = goodsService.searchGoods(keyword);
        log.info("返回结果->搜索商品结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findGoodsById(@PathVariable String id) {
        log.info("收到请求->获取商品,id:[{}]", id);
        Goods entity = goodsService.findGoodsById(id);
        log.info("返回结果->获取商品结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteGoodsById(@PathVariable String id) {
        log.info("收到请求->删除商品,id:[{}]", id);
        goodsService.deleteGoodsById(id);
        log.info("返回结果->删除商品结束");
        return ResponseDTO.returnSuccess();
    }
}

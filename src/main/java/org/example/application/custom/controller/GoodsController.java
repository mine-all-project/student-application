package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
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
    public ResponseDTO typeList() {
        log.info("收到请求->获取海产列表");
        List<Goods> list = goodsService.getAll();
        log.info("返回结果->获取海产列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/add")
    public ResponseDTO addGoods(@RequestBody Goods type) {
        log.info("收到请求->添加海产");
        goodsService.addGoods(type);
        log.info("返回结果->添加海产结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/update")
    public ResponseDTO updateGoods(@RequestBody Goods type) {
        log.info("收到请求->修改海产");
        goodsService.updateGoods(type);
        log.info("返回结果->修改海产结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findGoodsById(@PathVariable String id) {
        log.info("收到请求->获取海产,id:[{}]", id);
        Goods entity = goodsService.findGoodsById(id);
        log.info("返回结果->获取海产结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteGoodsById(@PathVariable String id) {
        log.info("收到请求->删除海产,id:[{}]", id);
        goodsService.deleteGoodsById(id);
        log.info("返回结果->删除海产结束");
        return ResponseDTO.returnSuccess();
    }
}

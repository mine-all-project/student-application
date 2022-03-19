package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.form.GoodsForm;
import org.example.application.custom.service.GoodsService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseDTO getList() {
        log.info("收到请求->获取商品列表");
        List<Goods> list = goodsService.getAll();
        log.info("返回结果->获取商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索商品列表,keywords:[{}]",keywords);
        List<Goods> list = goodsService.search(keywords);
        log.info("返回结果->搜索商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody GoodsForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布商品:[{}]",form);
        Goods entity = goodsService.save(request,form);
        log.info("返回结果->发布商品结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的商品列表");
        List<Goods> list = goodsService.getListByMine(request);
        log.info("返回结果->获取我发布的商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的商品,id:[{}]",id);
        goodsService.deleteById(id);
        log.info("返回结果->删除我发布的商品结束");
        return ResponseDTO.returnSuccess();
    }
}

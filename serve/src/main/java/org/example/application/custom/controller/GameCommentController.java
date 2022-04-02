package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.GameComment;
import org.example.application.custom.service.GameCommentService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/gameComment")
public class GameCommentController extends BaseController {
    private final GameCommentService gameCommentService;

    public GameCommentController(GameCommentService gameCommentService) {
        this.gameCommentService = gameCommentService;
    }

    @GetMapping("/list")
    @JwtIgnore
    public ResponseDTO getList() {
        log.info("收到请求->获取商品列表");
        List<GameComment> list = gameCommentService.getAll();
        log.info("返回结果->获取商品列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveGameComment(@RequestBody Map<String,String> form) {
        log.info("收到请求->保存商品:[{}]", form);
        GameComment entity = gameCommentService.saveGameComment(form);
        log.info("返回结果->保存商品结束:[{}]", entity);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/search")
    public ResponseDTO searchGameComment() {
        return getList();
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findGameCommentById(@PathVariable String id) {
        log.info("收到请求->获取商品,id:[{}]", id);
        GameComment entity = gameCommentService.findGameCommentById(id);
        log.info("返回结果->获取商品结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteGameCommentById(@PathVariable String id) {
        log.info("收到请求->删除商品,id:[{}]", id);
        gameCommentService.deleteGameCommentById(id);
        log.info("返回结果->删除商品结束");
        return ResponseDTO.returnSuccess();
    }
}

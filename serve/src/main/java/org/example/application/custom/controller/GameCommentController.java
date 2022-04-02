package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.GameComment;
import org.example.application.custom.service.GameCommentService;
import org.example.application.common.ResponseDTO;
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
    public ResponseDTO getList() {
        List<GameComment> list = gameCommentService.getAll();
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveGameComment(@RequestBody Map<String,String> form) {
        GameComment entity = gameCommentService.saveGameComment(form);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/search")
    public ResponseDTO searchGameComment() {
        return getList();
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findGameCommentById(@PathVariable String id) {
        GameComment entity = gameCommentService.findGameCommentById(id);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteGameCommentById(@PathVariable String id) {
        gameCommentService.deleteGameCommentById(id);
        return ResponseDTO.returnSuccess();
    }
}

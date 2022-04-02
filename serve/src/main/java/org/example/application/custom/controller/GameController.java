package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.ResponseDTO;
import org.example.application.custom.entity.Game;
import org.example.application.custom.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/game")
public class GameController extends BaseController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        List<Game> list = gameService.getAll();
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveGoods(@RequestBody Game form) {
        Game entity = gameService.saveGame(form);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/search")
    public ResponseDTO searchGoods() {
        return getList();
    }

    @GetMapping("/search/{keyword}")
    public ResponseDTO searchGoods(@PathVariable String keyword) {
        List<Game> list = gameService.searchGame(keyword);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findGoodsById(@PathVariable String id) {
        Game entity = gameService.findGameById(id);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteGoodsById(@PathVariable String id) {
        gameService.deleteGameById(id);
        return ResponseDTO.returnSuccess();
    }
}

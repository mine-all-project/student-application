package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.service.FavoriteService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/favorite")
public class FavoriteController extends BaseController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/goods/add/{id}")
    public ResponseDTO addGoodsFavorite(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->商品加入收藏:[{}]", id);
        favoriteService.addGoodsFavorite(request, id);
        log.info("返回结果->商品加入收藏结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/goods/delete/{id}")
    public ResponseDTO deleteGoodsFavorite(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->商品移出收藏:[{}]", id);
        favoriteService.deleteGoodsFavorite(request, id);
        log.info("返回结果->商品移出收藏结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/paper/add/{id}")
    public ResponseDTO addPaperFavorite(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->文章加入收藏:[{}]", id);
        favoriteService.addPaperFavorite(request, id);
        log.info("返回结果->文章加入收藏结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/paper/delete/{id}")
    public ResponseDTO deletePaperFavorite(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->文章移出收藏:[{}]", id);
        favoriteService.deletePaperFavorite(request, id);
        log.info("返回结果->文章移出收藏结束");
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/get")
    public ResponseDTO getFavorite(HttpServletRequest request) {
        log.info("收到请求->获取收藏");
        Map<String,String> data = favoriteService.getFavorite(request);
        log.info("返回结果->获取收藏结束,[{}]", data);
        return ResponseDTO.returnSuccess(data);
    }
}

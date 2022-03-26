package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.FavoriteService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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


    @GetMapping("/goods/get")
    public ResponseDTO getGoodsFavorite(HttpServletRequest request) {
        log.info("收到请求->获取我收藏的商品");
        List<Goods> list = favoriteService.getGoodsFavorite(request);
        log.info("返回结果->获取我收藏的商品结束:[{}]",list);
        return ResponseDTO.returnSuccess(list);
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

    @GetMapping("/paper/get")
    public ResponseDTO getPaperFavorite(HttpServletRequest request) {
        log.info("收到请求->获取我收藏的文章");
        List<Map> list = favoriteService.getPaperFavorite(request);
        log.info("返回结果->获取我收藏的文章结束:[{}]",list);
        return ResponseDTO.returnSuccess(list);
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

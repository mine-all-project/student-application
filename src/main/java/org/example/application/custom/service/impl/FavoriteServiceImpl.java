package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.FavoriteDAO;
import org.example.application.custom.entity.Favorite;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.FavoriteService;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDAO favoriteDAO;
    private final GoodsService goodsService;
    private final PaperService paperService;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public FavoriteServiceImpl(FavoriteDAO favoriteDAO, GoodsService goodsService, PaperService paperService, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.favoriteDAO = favoriteDAO;
        this.goodsService = goodsService;
        this.paperService = paperService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public void addPaperFavorite(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        Map paper = paperService.findPaperById(id);
        if (null != favorite) {
            favorite.setPaperIds(favorite.getPaperIds() + paper.get("id") + ",");
            favoriteDAO.updateFavorite(favorite);
        } else {
            favorite = new Favorite();
            favorite.setId(UUID.randomUUID().toString());
            favorite.setUserId(user.getId());
            favorite.setGoodsIds(",");
            favorite.setPaperIds(paper.get("id") + ",");
            favoriteDAO.addFavorite(favorite);
        }
    }

    @Override
    public void deletePaperFavorite(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        Map paper = paperService.findPaperById(id);
        if (null != favorite) {
            favorite.setPaperIds(favorite.getPaperIds().replace(paper.get("id").toString(), "") + ",");
            favoriteDAO.updateFavorite(favorite);
        } else {
            favorite = new Favorite();
            favorite.setId(UUID.randomUUID().toString());
            favorite.setUserId(user.getId());
            favorite.setGoodsIds("");
            favorite.setPaperIds("");
            favoriteDAO.addFavorite(favorite);
        }
    }

    @Override
    public void addGoodsFavorite(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        Goods goods = goodsService.findGoodsById(id);
        if (null != favorite) {
            favorite.setGoodsIds(favorite.getGoodsIds() + goods.getId() + ",");
            favoriteDAO.updateFavorite(favorite);
        } else {
            favorite = new Favorite();
            favorite.setId(UUID.randomUUID().toString());
            favorite.setUserId(user.getId());
            favorite.setGoodsIds(goods.getId() + ",");
            favorite.setPaperIds(",");
            favoriteDAO.addFavorite(favorite);
        }
    }

    @Override
    public void deleteGoodsFavorite(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        Goods goods = goodsService.findGoodsById(id);
        if (null != favorite) {
            favorite.setGoodsIds(favorite.getGoodsIds().replace(goods.getId(), "") + ",");
            favoriteDAO.updateFavorite(favorite);
        } else {
            favorite = new Favorite();
            favorite.setId(UUID.randomUUID().toString());
            favorite.setUserId(user.getId());
            favorite.setGoodsIds("");
            favorite.setPaperIds("");
            favoriteDAO.addFavorite(favorite);
        }
    }


    @Override
    public Map<String, String> getFavorite(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        Map<String, String> map = new HashMap<>();
        if (null != favorite) {
            map.put("goodsIds", favorite.getGoodsIds());
            map.put("paperIds", favorite.getPaperIds());
        }
        return map;
    }

    @Override
    public List<Goods> getGoodsFavorite(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        List<String> ids = Arrays.asList(favorite.getGoodsIds().split(","));
        return goodsService.selectByIds(ids);
    }

    @Override
    public List<Map> getPaperFavorite(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Favorite favorite = favoriteDAO.selectByUserId(user.getId());
        List<String> ids = Arrays.asList(favorite.getPaperIds().split(","));
        return paperService.selectByIds(ids);
    }
}

package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.GoodsDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.GoodsService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDAO goodsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public GoodsServiceImpl(GoodsDAO goodsDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.goodsDAO = goodsDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Goods> getAll() {
        return goodsDAO.getAll();
    }

    @Override
    public Goods saveGoods(HttpServletRequest request, Goods entity) {
        checkGoodsAuth(request, jwtConfigure, userDAO);
        return goodsDAO.saveGoods(entity);
    }


    @Override
    public Goods findGoodsById(String id) {
        return goodsDAO.findGoodsById(id);
    }

    @Override
    public void deleteGoodsById(HttpServletRequest request, String id) {
        checkGoodsAuth(request, jwtConfigure, userDAO);
        goodsDAO.deleteGoodsById(id);
    }

    @Override
    public List<Goods> searchGoods(String keyword) {
        return goodsDAO.findGoodsByName(keyword);
    }
}

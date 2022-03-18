package org.example.application.custom.service.impl;

import org.example.application.custom.dao.GoodsDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDAO goodsDAO;

    public GoodsServiceImpl(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @Override
    public List<Goods> getAll() {
        return goodsDAO.getAll();
    }
}

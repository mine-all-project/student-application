package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.GoodsRepository;
import org.example.application.custom.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsDAO extends BaseDAO {
    private final GoodsRepository goodsRepository;

    public GoodsDAO(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }
}

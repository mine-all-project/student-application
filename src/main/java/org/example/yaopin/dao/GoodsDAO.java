package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.GoodsRepository;
import org.example.yaopin.entity.Goods;
import org.springframework.stereotype.Repository;

@Repository
/**
 * 药品DAO
 */
public class GoodsDAO {
    private final GoodsRepository goodsRepository;

    public GoodsDAO(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public Goods saveData(Goods goods) {
        return goodsRepository.saveAndFlush(goods);
    }
}

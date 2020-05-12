package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.GoodsRepository;
import org.example.yaopin.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Goods findDataById(String id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public List<Goods> getAllByFlag() {
        return goodsRepository.findAllByDelFlag(1);
    }
}

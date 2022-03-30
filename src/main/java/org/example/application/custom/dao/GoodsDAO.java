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

    public Goods saveGoods(Goods entity) {
        return goodsRepository.saveAndFlush(entity);
    }

    public Goods findGoodsById(String id) {
        return goodsRepository.findById(id).orElse(new Goods());
    }

    public void deleteGoodsById(String id) {
        goodsRepository.deleteById(id);
    }

    public List<Goods> findGoodsByName(String keyword) {
        return goodsRepository.findByNameContains(keyword);
    }
}

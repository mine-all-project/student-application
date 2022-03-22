package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.GoodsMapper;
import org.example.application.custom.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GoodsDAO extends BaseDAO {
    private final GoodsMapper goodsMapper;

    public GoodsDAO(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public List<Goods> getAll() {
        return goodsMapper.findAll();
    }

    public void addGoods(Goods type) {
        type.setId(UUID.randomUUID().toString());
        goodsMapper.insert(type);
    }
    public void updateGoods(Goods type) {
        goodsMapper.updateByPrimaryKeySelective(type);
    }

    public Goods findGoodsById(String id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    public void deleteGoodsById(String id) {
        goodsMapper.deleteByPrimaryKey(id);
    }
}

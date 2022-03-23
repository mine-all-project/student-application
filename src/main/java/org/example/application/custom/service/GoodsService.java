package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;

import java.util.List;

public interface GoodsService extends BaseService {
    List<Goods> getAll();

    void addGoods(Goods entity);

    Goods findGoodsById(String id);

    void deleteGoodsById(String id);

    void updateGoods(Goods entity);

    List<Goods> selectByIds(List<String> goods_ids);

}

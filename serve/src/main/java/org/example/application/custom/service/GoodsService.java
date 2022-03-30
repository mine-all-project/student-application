package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;

import java.util.List;

public interface GoodsService extends BaseService {
    List<Goods> getAll();

    Goods saveGoods(Goods entity);

    Goods findGoodsById(String id);

    void deleteGoodsById(String id);

    List<Goods> searchGoods(String keyword);
}

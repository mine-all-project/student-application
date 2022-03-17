package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.GoodsRepository;
import org.springframework.stereotype.Component;

@Component
public class GoodsDAO extends BaseDAO {
    private final GoodsRepository goodsRepository;

    public GoodsDAO(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

}

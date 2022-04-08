package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsService extends BaseService {
    List<Goods> getAll();

    Goods saveGoods(HttpServletRequest request, Goods entity);

    Goods findGoodsById(String id);

    void deleteGoodsById(HttpServletRequest request,String id);

    List<Goods> searchGoods(String keyword);
}

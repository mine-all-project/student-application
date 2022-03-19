package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.form.GoodsForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsService extends BaseService {
    List<Goods> getAll();

    Goods save(HttpServletRequest request,GoodsForm form);

    List<Goods> search(String keywords);

    List<Goods> getListByMine(HttpServletRequest request);

    void deleteById(String id);
}

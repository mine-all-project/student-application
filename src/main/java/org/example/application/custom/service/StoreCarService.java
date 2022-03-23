package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StoreCarService extends BaseService {

    void addStoreCar(HttpServletRequest request, String goodsId);

    void deleteStoreCar(HttpServletRequest request, String goodsId);

    List<Goods> getStoreCar(HttpServletRequest request);

    void cleanStoreCar(HttpServletRequest request);
}

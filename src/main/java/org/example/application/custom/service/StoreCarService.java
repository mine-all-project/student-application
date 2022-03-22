package org.example.application.custom.service;

import org.example.application.common.BaseService;

import javax.servlet.http.HttpServletRequest;

public interface StoreCarService extends BaseService {

    void addStoreCar(HttpServletRequest request, String goodsId);

    void deleteStoreCar(HttpServletRequest request, String goodsId);
}

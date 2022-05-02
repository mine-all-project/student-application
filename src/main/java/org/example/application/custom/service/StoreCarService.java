package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.StoreCar;

import javax.servlet.http.HttpServletRequest;

public interface StoreCarService extends BaseService {
    void addStoreCar(HttpServletRequest request, String id);

    void removeStoreCar(HttpServletRequest request, String index);

    StoreCar getStoreCar(HttpServletRequest request);
}

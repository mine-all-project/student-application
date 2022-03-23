package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.StoreCarDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.entity.StoreCar;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class StoreCarServiceImpl implements StoreCarService {
    private final StoreCarDAO storeCarDAO;
    private final GoodsService goodsService;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public StoreCarServiceImpl(StoreCarDAO storeCarDAO, GoodsService goodsService, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.storeCarDAO = storeCarDAO;
        this.goodsService = goodsService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public void addStoreCar(HttpServletRequest request, String goodsId) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.selectByUserId(user.getId());
        Goods goods = goodsService.findGoodsById(goodsId);
        if (null != storeCar) {
            storeCar.setGoodsIds(storeCar.getGoodsIds() + goods.getId() + ",");
            storeCarDAO.updateStoreCar(storeCar);
        } else {
            storeCar = new StoreCar();
            storeCar.setId(UUID.randomUUID().toString());
            storeCar.setUserId(user.getId());
            storeCar.setGoodsIds(goods.getId() + ",");
            storeCarDAO.addStoreCar(storeCar);
        }
    }

    @Override
    public void deleteStoreCar(HttpServletRequest request, String goodsId) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.selectByUserId(user.getId());
        Goods goods = goodsService.findGoodsById(goodsId);
        if (null != storeCar) {
            storeCar.setGoodsIds(storeCar.getGoodsIds().replace(goods.getId(), "") + ",");
            storeCarDAO.updateStoreCar(storeCar);
        } else {
            storeCar = new StoreCar();
            storeCar.setId(UUID.randomUUID().toString());
            storeCar.setUserId(user.getId());
            storeCar.setGoodsIds("");
            storeCarDAO.addStoreCar(storeCar);
        }
    }

    @Override
    public List<Goods> getStoreCar(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.selectByUserId(user.getId());
        if (null != storeCar) {
            String[] good_ids = storeCar.getGoodsIds().split(",");
            List<String> ids = new ArrayList<>();
            for (String good_id : good_ids) {
                if (!StringUtils.isEmpty(good_id)) {
                    ids.add(good_id);
                }
            }
            return goodsService.selectByIds(ids);
        } else {
            return Collections.emptyList();
        }
    }
}

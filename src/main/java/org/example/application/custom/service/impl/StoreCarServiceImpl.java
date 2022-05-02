package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.GoodsDAO;
import org.example.application.custom.dao.StoreCarDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.entity.StoreCar;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class StoreCarServiceImpl implements StoreCarService {
    private final GoodsDAO goodsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final StoreCarDAO storeCarDAO;
    private final JwtConfigure jwtConfigure;

    public StoreCarServiceImpl(GoodsDAO goodsDAO, UserDAO userDAO, StoreCarDAO storeCarDAO, JwtConfigure jwtConfigure) {
        this.goodsDAO = goodsDAO;
        this.userDAO = userDAO;
        this.storeCarDAO = storeCarDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public void addStoreCar(HttpServletRequest request, String id) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.findByUser(sysUser);
        List<Goods> goodsList = storeCar.getGoods();
        Goods goods = goodsDAO.findGoodsById(id);
        goodsList.add(goods);
        storeCar.setGoods(goodsList);
        storeCarDAO.save(storeCar);
    }

    @Override
    public void removeStoreCar(HttpServletRequest request, String index) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.findByUser(sysUser);
        List<Goods> goodsList = storeCar.getGoods();
        goodsList.remove(Integer.valueOf(index).intValue());
        storeCar.setGoods(goodsList);
        storeCarDAO.save(storeCar);
    }

    @Override
    public StoreCar getStoreCar(HttpServletRequest request) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return storeCarDAO.findByUser(sysUser);
    }
}

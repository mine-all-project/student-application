package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.GoodsDAO;
import org.example.application.custom.dao.StoreCarDAO;
import org.example.application.custom.dao.StoreCarItemDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.entity.StoreCar;
import org.example.application.custom.service.StoreCarService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StoreCarServiceImpl implements StoreCarService {
    private final GoodsDAO goodsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final StoreCarDAO storeCarDAO;
    private final StoreCarItemDAO storeCarItemDAO;
    private final JwtConfigure jwtConfigure;

    public StoreCarServiceImpl(GoodsDAO goodsDAO, UserDAO userDAO,
                               StoreCarDAO storeCarDAO, StoreCarItemDAO storeCarItemDAO,
                               JwtConfigure jwtConfigure) {
        this.goodsDAO = goodsDAO;
        this.userDAO = userDAO;
        this.storeCarDAO = storeCarDAO;
        this.storeCarItemDAO = storeCarItemDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public void addStoreCar(HttpServletRequest request, String id) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.findByUser(sysUser);

        List<StoreCar.StoreCarItem> goodsList = storeCar.getItem();
        Goods goods = goodsDAO.findGoodsById(id);
        boolean isExist = false;
        for (StoreCar.StoreCarItem carItem : goodsList) {
            if (carItem.getGoods().getId().equals(goods.getId())) {
                isExist = true;
                carItem.setCountNum(carItem.getCountNum() + 1);
            }
        }
        if (!isExist) {
            StoreCar.StoreCarItem storeCarItem = new StoreCar.StoreCarItem();
            storeCarItem.setGoods(goods);
            storeCarItem.setCountNum(1);
            storeCarItem = storeCarItemDAO.save(storeCarItem);
            goodsList.add(storeCarItem);
        }
        storeCar.setItem(goodsList);
        storeCarDAO.save(storeCar);
    }

    @Override
    public void changeStoreCar(HttpServletRequest request, StoreCar.StoreCarItem entity) {
        StoreCar.StoreCarItem item = storeCarItemDAO.findById(entity.getId());
        item.setCountNum(entity.getCountNum());
        storeCarItemDAO.save(item);
    }

    @Override
    public void removeStoreCar(HttpServletRequest request, String id) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        StoreCar storeCar = storeCarDAO.findByUser(sysUser);
        List<StoreCar.StoreCarItem> goodsList = storeCar.getItem();
        goodsList = goodsList.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList());
        storeCar.setItem(goodsList);
        storeCarDAO.save(storeCar);
        storeCarItemDAO.deleteById(id);
    }

    @Override
    public StoreCar getStoreCar(HttpServletRequest request) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return storeCarDAO.findByUser(sysUser);
    }
}

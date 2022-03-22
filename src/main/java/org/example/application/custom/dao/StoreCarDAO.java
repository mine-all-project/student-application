package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.StoreCarMapper;
import org.example.application.custom.entity.StoreCar;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StoreCarDAO extends BaseDAO {
    private final StoreCarMapper storeCarMapper;

    public StoreCarDAO(StoreCarMapper storeCarMapper) {
        this.storeCarMapper = storeCarMapper;
    }


    public void addStoreCar(StoreCar type) {
        type.setId(UUID.randomUUID().toString());
        storeCarMapper.insert(type);
    }
    public void updateStoreCar(StoreCar type) {
        storeCarMapper.updateByPrimaryKeySelective(type);
    }

    public StoreCar selectByUserId(String userId) {
        return storeCarMapper.selectByUserId(userId);
    }
}

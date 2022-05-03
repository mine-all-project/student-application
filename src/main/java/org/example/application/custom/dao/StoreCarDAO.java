package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.StoreCarRepository;
import org.example.application.custom.entity.StoreCar;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StoreCarDAO extends BaseDAO {
    private final StoreCarRepository storeCarRepository;

    public StoreCarDAO(StoreCarRepository storeCarRepository) {
        this.storeCarRepository = storeCarRepository;
    }

    public StoreCar findByUser(SysUser user) {
        StoreCar storeCar = new StoreCar();
        storeCar.setItem(new ArrayList<>());
        storeCar.setUser(user);
        return storeCarRepository.findByUser(user).orElse(storeCar);
    }

    public void save(StoreCar entity) {
        storeCarRepository.save(entity);
    }
}

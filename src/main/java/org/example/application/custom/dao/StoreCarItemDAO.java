package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.StoreCarItemRepository;
import org.example.application.custom.entity.StoreCar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreCarItemDAO extends BaseDAO {
    private final StoreCarItemRepository storeCarItemRepository;

    public StoreCarItemDAO(StoreCarItemRepository storeCarItemRepository) {
        this.storeCarItemRepository = storeCarItemRepository;
    }

    public StoreCar.StoreCarItem save(StoreCar.StoreCarItem entity) {
        return storeCarItemRepository.save(entity);
    }

    public StoreCar.StoreCarItem findById(String id) {
        return storeCarItemRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        storeCarItemRepository.deleteById(id);
    }

    public List<StoreCar.StoreCarItem> saveAll(List<StoreCar.StoreCarItem> list) {
        return storeCarItemRepository.saveAll(list);
    }
}

package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.StoragesRepository;
import org.example.yaopin.entity.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
/**
 * 库存DAO
 */
public class StoragesDAO {
    private final StoragesRepository storagesRepository;

    public StoragesDAO(StoragesRepository storagesRepository) {
        this.storagesRepository = storagesRepository;
    }

    public void saveData(Storages storages) {
        storagesRepository.saveAndFlush(storages);
    }
}

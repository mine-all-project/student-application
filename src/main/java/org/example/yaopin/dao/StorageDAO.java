package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.StorageRepository;
import org.example.yaopin.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * 库存DAO
 */
public class StorageDAO {
    private final StorageRepository storageRepository;

    public StorageDAO(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public void saveData(Storage storage) {
        storageRepository.saveAndFlush(storage);
    }

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }
}

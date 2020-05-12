//package org.example.yaopin.dao;
//
//import org.example.yaopin.dao.jpa.StorageRepository;
//import org.example.yaopin.entity.Message;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
///**
// * 库存DAO
// */
//public class StorageDAO {
//    private final StorageRepository storageRepository;
//
//    public StorageDAO(StorageRepository storageRepository) {
//        this.storageRepository = storageRepository;
//    }
//
//    public void saveData(Message storage) {
//        storageRepository.saveAndFlush(storage);
//    }
//
//    public List<Message> findAll() {
//        return storageRepository.findAll();
//    }
//}

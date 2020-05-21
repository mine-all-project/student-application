//package org.example.shiyanshi.dao;
//
//import org.example.shiyanshi.dao.jpa.GoodsRepository;
//import org.example.shiyanshi.dao.jpa.PurchasesRepository;
//import org.example.shiyanshi.entity.Purchases;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
///**
// * 采购DAO
// */
//public class PurchasesDAO {
//    private final PurchasesRepository purchasesRepository;
//    private final GoodsRepository goodsRepository;
//
//    public PurchasesDAO(PurchasesRepository purchasesRepository, GoodsRepository goodsRepository) {
//        this.purchasesRepository = purchasesRepository;
//        this.goodsRepository = goodsRepository;
//    }
//
//    public List<Purchases> findAll() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return purchasesRepository.findAll(sort);
//    }
//
//    public List<Purchases> getPurchasesListByStatus() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return purchasesRepository.findByStatusAndDelFlag(sort, 0, 0);
//    }
//    public List<Purchases> getPurchasesListByStatusNot() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return purchasesRepository.findByStatusNotAndDelFlag(sort, 0, 0);
//    }
//
//    public Purchases findById(String id) {
//        return purchasesRepository.findById(id).orElse(new Purchases());
//    }
//
//    public void saveData(Purchases purchases) {
//        purchasesRepository.saveAndFlush(purchases);
//    }
//
//    public void flagDelById(String id) {
//        Purchases purchases = purchasesRepository.findById(id).orElse(null);
//        assert purchases != null;
//        purchases.setDelFlag(1);
//        purchasesRepository.saveAndFlush(purchases);
//    }
//
//
//
//}

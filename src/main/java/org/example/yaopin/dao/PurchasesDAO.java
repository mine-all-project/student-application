package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.PurchasesRepository;
import org.example.yaopin.entity.Purchases;
import org.example.yaopin.form.PurchasesForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchasesDAO {
    private final PurchasesRepository purchasesRepository;

    public PurchasesDAO(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }


    public List<Purchases> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return purchasesRepository.findAll(sort);
    }

    public Purchases findById(String id) {
        return purchasesRepository.findById(id).orElse(new Purchases());
    }

    public void saveGoodsInfo(PurchasesForm form) {
        Purchases entity = form.toEntity();
        entity.setStatus(1);
        purchasesRepository.saveAndFlush(entity);
    }
//
//    public void removeLinesById(String id) {
//        linesRepository.deleteById(id);
//    }
//
//    public List<Linees> findByNumber(String number) {
//        return linesRepository.findByNumberLike("%" + number + "%");
//    }
}

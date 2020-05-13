package org.example.yaopin.dao;


import org.example.yaopin.dao.jpa.SalesRepository;
import org.example.yaopin.entity.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesDAO {
    private final SalesRepository salesRepository;

    public SalesDAO(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sales> getAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return salesRepository.findAll(sort);
    }


//    public Stands findById(String id) {
//        return standsRepository.findById(id).orElse(new Stands());
//    }
//
//    public void saveStands(StandsForm form) {
//        standsRepository.saveAndFlush(form.toEntity());
//    }
//
//    public void removeStandsById(String id) {
//        standsRepository.deleteById(id);
//    }
//
//    public Stands findByName(String name) {
//        return standsRepository.findByName(name).orElse(new Stands());
//    }
}

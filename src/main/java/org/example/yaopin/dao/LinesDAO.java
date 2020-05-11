package org.example.yaopin.dao;

import org.example.yaopin.form.LinesForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinesDAO {
//    private final LinesRepository linesRepository;
//
//    public LinesDAO(LinesRepository linesRepository) {
//        this.linesRepository = linesRepository;
//    }
//
//    public List<Linees> findAll() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return linesRepository.findAll(sort);
//    }
//
//    public Linees findById(String id) {
//        return linesRepository.findById(id).orElse(new Linees());
//    }
//
//    public void saveLines(LinesForm form) {
//        linesRepository.saveAndFlush(form.toEntity());
//    }
//
//    public void removeLinesById(String id) {
//        linesRepository.deleteById(id);
//    }
//
//    public List<Linees> findByNumber(String number) {
//        return linesRepository.findByNumberLike("%" + number + "%");
//    }
}

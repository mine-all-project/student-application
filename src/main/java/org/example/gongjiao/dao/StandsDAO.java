package org.example.gongjiao.dao;

import org.example.gongjiao.dao.jpa.StandsRepository;
import org.example.gongjiao.entity.Stands;
import org.example.gongjiao.form.StandsForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StandsDAO {
    private final StandsRepository standsRepository;

    public StandsDAO(StandsRepository standsRepository) {
        this.standsRepository = standsRepository;
    }

    public List<Stands> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return standsRepository.findAll(sort);
    }

    public Stands findById(String id) {
        return standsRepository.findById(id).orElse(new Stands());
    }

    public void saveStands(StandsForm form) {
        standsRepository.saveAndFlush(form.toEntity());
    }

    public void removeStandsById(String id) {
        standsRepository.deleteById(id);
    }

    public Stands findByName(String name) {
        return standsRepository.findByName(name).orElse(new Stands());
    }
}

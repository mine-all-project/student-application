package org.example.gongjiao.dao;

import org.example.gongjiao.dao.jpa.StandsRepository;
import org.example.gongjiao.entity.Stands;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StandsDAO {
    private final StandsRepository standsRepository;

    public StandsDAO(StandsRepository standsRepository) {
        this.standsRepository = standsRepository;
    }

    public List<Stands> findAll() {
        return standsRepository.findAll();
    }
}

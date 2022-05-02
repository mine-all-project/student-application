package com.dao;

import com.config.BaseDAO;
import com.entity.Jingxiaodian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
public class JingxiaodianDao extends BaseDAO {
    private final JingxiaodianRepository jingxiaodianRepository;

    public JingxiaodianDao(JingxiaodianRepository jingxiaodianRepository) {
        this.jingxiaodianRepository = jingxiaodianRepository;
    }

    public void save(Jingxiaodian entity) {
        jingxiaodianRepository.save(entity);
    }

    public List<Jingxiaodian> list() {
        return jingxiaodianRepository.findAll(desByCreateTime);
    }

    public void deleteById(String id) {
        jingxiaodianRepository.deleteById(id);
    }

    public Jingxiaodian findById(String id) {
        return jingxiaodianRepository.findById(id).orElse(null);
    }
}

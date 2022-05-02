package com.service.impl;

import com.dao.JingxiaodianDao;
import com.entity.Jingxiaodian;
import com.service.JingxiaodianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JingxiaodianServiceImpl implements JingxiaodianService {
    private final JingxiaodianDao jingxiaodianDao;

    public JingxiaodianServiceImpl(JingxiaodianDao jingxiaodianDao) {
        this.jingxiaodianDao = jingxiaodianDao;
    }

    @Override
    public void save(Jingxiaodian entity) {
        jingxiaodianDao.save(entity);
    }

    @Override
    public List<Jingxiaodian> getList() {
        return jingxiaodianDao.list();
    }

    @Override
    public void delete(List<String> ids) {
        for (String id : ids) {
            jingxiaodianDao.deleteById(id);
        }
    }

    @Override
    public Jingxiaodian info(String id) {
        return jingxiaodianDao.findById(id);
    }
}

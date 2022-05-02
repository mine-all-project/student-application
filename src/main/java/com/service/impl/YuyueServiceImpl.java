package com.service.impl;

import com.dao.JingxiaodianDao;
import com.dao.YuyueDao;
import com.entity.Jingxiaodian;
import com.entity.Yuyue;
import com.service.YuyueService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YuyueServiceImpl implements YuyueService {
    private final YuyueDao yuyueDao;
    private final JingxiaodianDao jingxiaodianDao;

    public YuyueServiceImpl(YuyueDao yuyueDao, JingxiaodianDao jingxiaodianDao) {
        this.yuyueDao = yuyueDao;
        this.jingxiaodianDao = jingxiaodianDao;
    }

    @Override
    public void save(Yuyue entity) {
        entity.setStatus("待处理");
        yuyueDao.save(entity);
    }

    @Override
    public List<Yuyue> getList(String type) {
        return yuyueDao.getList(type);
    }

    @Override
    public void finish(String id) {
        yuyueDao.finish(id);
    }

    @Override
    public void finish(String id, String storeid) {
         Jingxiaodian jingxiaodian = jingxiaodianDao.findById(storeid);
        Yuyue entity =  yuyueDao.findById(id);
        entity.setStore(jingxiaodian);
        entity.setStatus("已完成");
        yuyueDao.save(entity);
    }
}

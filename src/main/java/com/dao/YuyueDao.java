package com.dao;

import com.config.BaseDAO;
import com.entity.Jingxiaodian;
import com.entity.Yuyue;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class YuyueDao extends BaseDAO {
    private final YuyueRepository yuyueRepository;

    public YuyueDao(YuyueRepository yuyueRepository) {
        this.yuyueRepository = yuyueRepository;
    }

    public void save(Yuyue entity) {
        yuyueRepository.save(entity);
    }

    public List<Yuyue> getList(String type) {
        return yuyueRepository.findAllByType(type, desByCreateTime);
    }

    public void finish(String id) {
        Yuyue entity = yuyueRepository.findById(id).orElse(null);
        entity.setStatus("已完成");
        yuyueRepository.save(entity);
    }

    public Yuyue findById(String id) {
       return yuyueRepository.findById(id).orElse(null);
    }
}

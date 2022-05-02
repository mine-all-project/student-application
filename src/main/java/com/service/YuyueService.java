package com.service;


import com.entity.Jingxiaodian;
import com.entity.Yuyue;

import java.util.List;


public interface YuyueService {
    void save(Yuyue entity);

    List<Yuyue> getList(String type);

    void finish(String id);

    void finish(String id, String storeid);
}


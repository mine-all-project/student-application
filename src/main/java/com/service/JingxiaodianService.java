package com.service;


import com.entity.Jingxiaodian;

import java.util.List;

public interface JingxiaodianService  {

    void save(Jingxiaodian entity);

    List<Jingxiaodian> getList();

    void delete(List<String> ids);

    Jingxiaodian info(String id);
}


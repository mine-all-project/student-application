package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.example.application.custom.entity.Goods;

import java.util.List;
@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();
}

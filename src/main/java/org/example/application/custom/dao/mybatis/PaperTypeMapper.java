package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.example.application.custom.entity.PaperType;

import java.util.List;

@Mapper
public interface PaperTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(PaperType record);

    int insertSelective(PaperType record);

    PaperType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PaperType record);

    int updateByPrimaryKeyWithBLOBs(PaperType record);

    int updateByPrimaryKey(PaperType record);

    List<PaperType> findAll();
}

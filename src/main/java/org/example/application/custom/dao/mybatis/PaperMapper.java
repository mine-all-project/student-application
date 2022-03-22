package org.example.application.custom.dao.mybatis;

import org.example.application.custom.entity.Paper;

public interface PaperMapper {
    int deleteByPrimaryKey(String id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
}
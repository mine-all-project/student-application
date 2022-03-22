package org.example.application.custom.dao.mybatis;

import org.example.application.custom.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperMapper {
    int deleteByPrimaryKey(String id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

    List<Map> selectAll();
}

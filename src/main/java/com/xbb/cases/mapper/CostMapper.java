package com.xbb.cases.mapper;

import com.xbb.cases.entity.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    List<Cost> selectListByPaging(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("name") String name,
            @Param("types") String types
    );

    int selectCountByPaging(
            @Param("name") String name,
            @Param("types") String types
    );
}
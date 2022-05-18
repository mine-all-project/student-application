package com.xbb.cases.mapper;

import com.xbb.cases.entity.Leaves;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeavesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leaves record);

    int insertSelective(Leaves record);

    Leaves selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leaves record);

    int updateByPrimaryKey(Leaves record);

    List<Leaves> selectListByPaging(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("content") String content,
            @Param("userId") Integer userId
    );

    int selectCountByPaging(
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("content") String content,
            @Param("userId") Integer userId
    );
}
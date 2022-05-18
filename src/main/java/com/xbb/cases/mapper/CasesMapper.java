package com.xbb.cases.mapper;

import com.xbb.cases.entity.Cases;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cases record);

    int insertSelective(Cases record);

    Cases selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cases record);

    int updateByPrimaryKeyWithBLOBs(Cases record);

    int updateByPrimaryKey(Cases record);

    List<Cases> selectListByPaging(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("identityCard") String identityCard,
            @Param("title") String title,
            @Param("status") Integer status,
            @Param("casesType") Integer casesType
    );

    int selectCountByPaging(
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("identityCard") String identityCard,
            @Param("title") String title,
            @Param("status") Integer status,
            @Param("casesType") Integer casesType
    );

    Cases selectById(Integer id);
}
package com.xbb.cases.mapper;

import com.xbb.cases.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectListByPaging(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("sex") Integer sex,
            @Param("role") Integer role
    );

    int selectCountByPaging(
            @Param("name") String name,
            @Param("cellphone") String cellphone,
            @Param("sex") Integer sex,
            @Param("role") Integer role
    );

    User selectByUsername(String username);

    List<User> selectListByRole(@Param("role") Integer role);
}
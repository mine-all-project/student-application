package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Param;
import org.example.application.custom.entity.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectByUserId(@Param("userId") String userId);
}

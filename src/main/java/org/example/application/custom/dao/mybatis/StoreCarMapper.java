package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.application.custom.entity.StoreCar;
@Mapper
public interface StoreCarMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreCar record);

    int insertSelective(StoreCar record);

    StoreCar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreCar record);

    int updateByPrimaryKey(StoreCar record);

    StoreCar selectByUserId(@Param("user_id") String userId);
}

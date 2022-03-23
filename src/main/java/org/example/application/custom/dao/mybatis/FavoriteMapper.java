package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.example.application.custom.entity.Favorite;
@Mapper
public interface FavoriteMapper {
    int insert(Favorite record);

    int insertSelective(Favorite record);

    void updateByPrimaryKeySelective(Favorite type);

    Favorite selectByUserId(String userId);
}

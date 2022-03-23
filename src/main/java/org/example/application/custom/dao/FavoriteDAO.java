package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.FavoriteMapper;
import org.example.application.custom.entity.Favorite;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FavoriteDAO extends BaseDAO {
    private final FavoriteMapper favoriteMapper;

    public FavoriteDAO(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }


    public void addFavorite(Favorite type) {
        type.setId(UUID.randomUUID().toString());
        favoriteMapper.insert(type);
    }

    public void updateFavorite(Favorite type) {
        String goodsIds = type.getGoodsIds();
        String paperIds = type.getPaperIds();
        type.setGoodsIds(goodsIds.replace(",,", ","));
        type.setPaperIds(paperIds.replace(",,", ","));
        favoriteMapper.updateByPrimaryKeySelective(type);
    }

    public Favorite selectByUserId(String userId) {
        return favoriteMapper.selectByUserId(userId);
    }
}

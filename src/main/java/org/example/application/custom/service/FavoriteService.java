package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Goods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface FavoriteService extends BaseService {

    void deletePaperFavorite(HttpServletRequest request, String id);

    void addPaperFavorite(HttpServletRequest request, String id);

    void deleteGoodsFavorite(HttpServletRequest request, String id);

    void addGoodsFavorite(HttpServletRequest request, String id);

    Map<String,String> getFavorite(HttpServletRequest request);
}

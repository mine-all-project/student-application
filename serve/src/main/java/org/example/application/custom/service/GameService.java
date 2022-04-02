package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Game;

import java.util.List;

public interface GameService extends BaseService {
    List<Game> getAll();

    Game saveGame(Game entity);

    Game findGameById(String id);

    void deleteGameById(String id);

    List<Game> searchGame(String keyword);
}

package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.GameDAO;
import org.example.application.custom.entity.Game;
import org.example.application.custom.service.GameService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {
    private final GameDAO gameDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public GameServiceImpl(GameDAO gameDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Game> getAll() {
        return gameDAO.getAll();
    }

    @Override
    public Game saveGame(Game entity) {
        return gameDAO.saveGame(entity);
    }


    @Override
    public Game findGameById(String id) {
        return gameDAO.findGameById(id);
    }

    @Override
    public void deleteGameById(String id) {
        gameDAO.deleteGameById(id);
    }

    @Override
    public List<Game> searchGame(String keyword) {
        return gameDAO.findGameByName(keyword);
    }
}

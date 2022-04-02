package org.example.application.custom.dao;

import org.example.application.custom.dao.jpa.GameRepository;
import org.example.application.custom.entity.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameDAO {
    private final GameRepository gameRepository;

    public GameDAO(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game saveGame(Game entity) {
        return gameRepository.saveAndFlush(entity);
    }

    public Game findGameById(String id) {
        return gameRepository.findById(id).orElse(new Game());
    }

    public void deleteGameById(String id) {
        gameRepository.deleteById(id);
    }

    public List<Game> findGameByName(String keyword) {
        return gameRepository.findByNameContains(keyword);
    }
}

package org.example.application.custom.dao;

import org.example.application.custom.dao.jpa.GameCommentRepository;
import org.example.application.custom.entity.GameComment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameCommentDAO {
    private final GameCommentRepository gameCommentRepository;

    public GameCommentDAO(GameCommentRepository gameCommentRepository) {
        this.gameCommentRepository = gameCommentRepository;
    }

    public List<GameComment> getAll() {
        return gameCommentRepository.findAll();
    }

    public GameComment saveGameComment(GameComment entity) {
        return gameCommentRepository.saveAndFlush(entity);
    }

    public GameComment findGameCommentById(String id) {
        return gameCommentRepository.findById(id).orElse(new GameComment());
    }

    public void deleteGameCommentById(String id) {
        gameCommentRepository.deleteById(id);
    }
}

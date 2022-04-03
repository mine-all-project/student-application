package org.example.application.custom.service.impl;

import org.example.application.custom.dao.GameCommentDAO;
import org.example.application.custom.dao.GameDAO;
import org.example.application.custom.entity.Game;
import org.example.application.custom.entity.GameComment;
import org.example.application.custom.service.GameCommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GameCommentServiceImpl implements GameCommentService {
    private final GameCommentDAO gameCommentDAO;
    private final GameDAO gameDAO;

    public GameCommentServiceImpl(GameCommentDAO gameCommentDAO, GameDAO gameDAO) {
        this.gameCommentDAO = gameCommentDAO;
        this.gameDAO = gameDAO;
    }

    @Override
    public List<GameComment> getAll() {
        return gameCommentDAO.getAll();
    }

    @Override
    public GameComment saveGameComment(Map<String, String> entity) {
        Game game = gameDAO.findGameById(entity.getOrDefault("gameId", ""));
        GameComment gameComment = new GameComment(entity.getOrDefault("content", ""));
        gameComment = gameCommentDAO.saveGameComment(gameComment);
        List<GameComment> commentList = game.getGameComments();
        commentList.add(gameComment);
        int score = Integer.parseInt(entity.getOrDefault("score", "0"));
        int scoreCount = game.getScoreCount();
        scoreCount++;
        game.setScore(game.getScore() + score);
        game.setScoreCount(scoreCount);
        game.setGameComments(commentList);
        gameDAO.saveGame(game);
        return gameComment;
    }


    @Override
    public GameComment findGameCommentById(String id) {
        return gameCommentDAO.findGameCommentById(id);
    }

    @Override
    public void deleteGameCommentById(String id) {
        gameCommentDAO.deleteGameCommentById(id);
    }

}

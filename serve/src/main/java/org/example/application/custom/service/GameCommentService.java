package org.example.application.custom.service;

import org.example.application.custom.entity.GameComment;

import java.util.List;
import java.util.Map;

public interface GameCommentService  {
    List<GameComment> getAll();

    GameComment saveGameComment(Map<String,String> entity);

    GameComment findGameCommentById(String id);

    void deleteGameCommentById(String id);

}

package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.GameComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCommentRepository extends JpaRepository<GameComment, String> {
}

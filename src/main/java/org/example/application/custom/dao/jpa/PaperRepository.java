package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findAllByTitleContains(String keyword);
}

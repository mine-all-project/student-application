package org.example.fangwuzulin.dao;

import org.example.fangwuzulin.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findAllByKeyWord(String keyWord);
}

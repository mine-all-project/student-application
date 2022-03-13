package org.example.textreader.custom.dao.jpa;

import org.example.textreader.custom.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {
}

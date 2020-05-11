package org.example.yaopin.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinesRepository extends JpaRepository<Linees, String> {

    List<Linees> findByNumberLike(String number);
}

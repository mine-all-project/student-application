package org.example.gongjiao.dao.jpa;

import org.example.gongjiao.entity.Linees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinesRepository extends JpaRepository<Linees, String> {

    List<Linees> findByNumberLike(String number);
}

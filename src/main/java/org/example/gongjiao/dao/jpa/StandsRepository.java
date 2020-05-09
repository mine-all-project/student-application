package org.example.gongjiao.dao.jpa;

import org.example.gongjiao.entity.Stands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StandsRepository extends JpaRepository<Stands, String> {
    Optional<Stands> findByName(String name);
}

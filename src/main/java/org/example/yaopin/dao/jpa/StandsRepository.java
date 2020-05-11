package org.example.yaopin.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StandsRepository extends JpaRepository<Stands, String> {
    Optional<Stands> findByName(String name);
}

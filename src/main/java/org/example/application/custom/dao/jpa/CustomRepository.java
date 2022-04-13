package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Custom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomRepository extends JpaRepository<Custom, String> {
    @Query(nativeQuery = true, value = "select * from custom limit 1")
    Optional<Custom> get();
}

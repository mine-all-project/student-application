package org.example.fangwuzulin.dao;

import org.example.fangwuzulin.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    List<Animal> findAllByType(String type);
}

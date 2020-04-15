package org.example.yangjichang.dao;

import org.example.yangjichang.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    List<Animal> findAllByType(String type);
}

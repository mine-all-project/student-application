package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findAllByNameLike(String name, Sort desByCreateTime);
}

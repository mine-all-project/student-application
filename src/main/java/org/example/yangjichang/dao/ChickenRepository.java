package org.example.yangjichang.dao;

import org.example.yangjichang.entity.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChickenRepository extends JpaRepository<Chicken, String> {

}

package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findByNameContains(String name);
}

package org.example.tuole.dao;

import org.example.tuole.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findAllByKeyWord(String video);

}

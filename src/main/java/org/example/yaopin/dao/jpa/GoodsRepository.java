package org.example.yaopin.dao.jpa;


import org.example.yaopin.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findAllByDelFlag(int i);
}

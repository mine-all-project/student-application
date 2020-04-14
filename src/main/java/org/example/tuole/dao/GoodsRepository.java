package org.example.tuole.dao;

import org.example.tuole.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 销售信息持久层
 *
 *
 * 2019/7/4 1422:51
 *
 *
 * pc-name 29404
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findAllByKeyWord(String video);

}

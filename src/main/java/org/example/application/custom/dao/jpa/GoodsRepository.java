package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Goods;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单持久层
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findAllByNameLike(String keywords, Sort sort);
    List<Goods> findAllByPublisher(SysUser publisher, Sort sort);
}

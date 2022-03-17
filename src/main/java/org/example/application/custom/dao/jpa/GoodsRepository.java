package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Goods;
import org.example.application.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单持久层
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
}

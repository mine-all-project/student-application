package cn.crabapples.tuole.dao;


import cn.crabapples.tuole.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO 系统菜单持久层
 *
 *
 * 3/2/20 12:15 AM
 *
 *
 * pc-name root
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    List<SysMenu> findByParentId(String parentId);

    List<SysMenu> findByParentIdIsNull();
}

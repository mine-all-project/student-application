package org.example.application.custom.dao.jpa;

import org.example.application.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单持久层
 */
@Repository
public interface SysMenuRepository extends JpaRepository<org.example.application.custom.SysMenu, String> {

    List<org.example.application.custom.SysMenu> findByParentId(String parentId);

    List<SysMenu> findByParentIdIsNull();
}

package org.example.fangwuzulin.dao;


import org.example.fangwuzulin.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    List<SysMenu> findByParentId(String parentId);

    List<SysMenu> findByParentIdIsNull();
}

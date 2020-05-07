package org.example.gongjiao.dao.jpa;


import org.example.gongjiao.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    List<SysMenu> findByParentId(String parentId);

    List<SysMenu> findByParentIdIsNull();
}

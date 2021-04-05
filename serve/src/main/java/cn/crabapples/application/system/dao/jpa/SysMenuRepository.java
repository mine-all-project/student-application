package cn.crabapples.application.system.dao.jpa;

import cn.crabapples.application.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 系统菜单持久层
 *
 * @author Mr.He
 * 3/2/20 12:15 AM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    List<SysMenu> findByParentId(String parentId);

    List<SysMenu> findByParentIdIsNull();
}

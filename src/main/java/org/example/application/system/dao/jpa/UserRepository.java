package org.example.application.system.dao.jpa;

import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * 系统用户持久层
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByUsername(String username);

    List<SysUser> findByDelFlag(Sort sort, int delFlag);
}

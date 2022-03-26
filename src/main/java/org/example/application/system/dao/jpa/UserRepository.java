package org.example.application.system.dao.jpa;

import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * 系统用户持久层
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByUsername(String username);

    /**
     * 禁用用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set status = 1 where id = :id")
    @Modifying
    void unActiveUser(@Param("id") String id);

    /**
     * 激活用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set status = 0 where id = :id")
    @Modifying
    void activeUser(@Param("id") String id);

    List<SysUser> findByIdNotAndStatusAndDelFlag(String id, int status, int delFlag);

    List<SysUser> findByIdInAndStatusAndDelFlag(List<String> ids, int status, int delFlag);

    List<SysUser> findByDelFlag(Sort sort, int delFlag);


}

package org.example.shiyanshi.dao.jpa;

import org.example.shiyanshi.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByUsername(String username);

    Optional<SysUser> findByUsernameAndStatus(String username, int status);

    List<SysUser> findByName(String name);

    List<SysUser> findByNameLike(String name);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Query("update SysUser set delFlag = 1 where id=:name")
    @Modifying
    void delUser(@Param("name") String id);

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

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @param status   状态
     * @param delFlag  删除标记
     * @return 查询到的用户
     */
    Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);


    Optional<SysUser> findByUsernameAndPassword(String username, String password);
}

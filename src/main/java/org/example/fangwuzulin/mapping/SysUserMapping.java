package org.example.fangwuzulin.mapping;

import org.apache.ibatis.annotations.Select;
import org.example.fangwuzulin.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapping {
    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username, String password);

    Integer insertUser(SysUser sysUser);

    Integer updateUserInfo(SysUser sysUser);

    SysUser findById(String id);

    List<SysUser> findAllUser();

    Integer removeUserById(String id);
}

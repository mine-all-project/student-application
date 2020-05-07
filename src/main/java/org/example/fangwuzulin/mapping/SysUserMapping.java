package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapping {
    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username, String password);

    Integer insertUser(SysUser sysUser);

    Integer updateUserInfo(SysUser sysUser);

    SysUser findById(String id);
}

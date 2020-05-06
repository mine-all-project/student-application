package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapping {
    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username, String password);

    void addUser(SysUser sysUser);
}

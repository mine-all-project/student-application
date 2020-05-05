package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.SysUser;

public interface SysUserMapping {
    SysUser findByUsernameAndPassword(String username,String password);
}

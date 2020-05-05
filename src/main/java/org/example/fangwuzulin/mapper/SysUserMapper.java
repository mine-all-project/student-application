package org.example.fangwuzulin.mapper;

import org.example.fangwuzulin.entity.SysUser;

public interface SysUserMapper {
    SysUser findByUsernameAndPassword(String username,String password);
}

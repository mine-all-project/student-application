package org.example.gongjiao.dao;

import org.example.gongjiao.dao.jpa.SysUserRepository;
import org.example.gongjiao.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserDAO {
    private final SysUserRepository sysUserRepository;

    public SysUserDAO(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public SysUser findByUsernameAndPassword(String username, String password) {
        return sysUserRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username).orElse(null);
    }

    public void insertUser(SysUser sysUser) {
        SysUser user = sysUserRepository.saveAndFlush(sysUser);
    }

    public void updateUserInfo(SysUser sysUser) {
        sysUserRepository.saveAndFlush(sysUser);
    }
}

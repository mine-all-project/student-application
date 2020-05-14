package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.SysUserRepository;
import org.example.yaopin.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public SysUser insertUser(SysUser sysUser) {
        SysUser user = sysUserRepository.saveAndFlush(sysUser);
        return user;
    }

    public void updateUserInfo(SysUser sysUser) {
        sysUserRepository.saveAndFlush(sysUser);
    }

    public List<SysUser> getAll() {
        return sysUserRepository.findAll();
    }

    public SysUser findById(String id) {
        return sysUserRepository.findById(id).orElse(null);
    }

    public SysUser saveAndFlush(SysUser sysUser) {
        return sysUserRepository.saveAndFlush(sysUser);
    }

    public void deleteById(String id) {
        sysUserRepository.deleteById(id);
    }
}

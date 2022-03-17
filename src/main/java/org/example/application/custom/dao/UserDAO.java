package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.UserRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAO extends BaseDAO {
    private final org.example.application.custom.dao.jpa.UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public org.example.application.custom.SysUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public org.example.application.custom.SysUser save(org.example.application.custom.SysUser user) {
        return userRepository.save(user);
    }

    public org.example.application.custom.SysUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<org.example.application.custom.SysUser> findByIds(List<String> ids) {
        return userRepository.findByIdInAndStatusAndDelFlag(ids, 0, NOT_DEL);
    }

    @Transactional
    public void delUser(String id) {
        userRepository.deleteById(id);
    }

    public List<org.example.application.custom.SysUser> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public void unActiveUser(String id) {
        userRepository.unActiveUser(id);
    }

    @Transactional
    public void activeUser(String id) {
        userRepository.activeUser(id);
    }

    public org.example.application.custom.SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag).orElse(null);
    }

    public List<org.example.application.custom.SysUser> findAll() {
        return userRepository.findByDelFlag(ascByCreateTime, NOT_DEL);

    }

    public List<SysUser> getUserList(String userId, int status) {
        return userRepository.findByIdNotAndStatusAndDelFlag(userId, status, NOT_DEL);
    }
}

package org.example.application.system.dao;

import org.example.application.common.BaseDAO;
import org.example.application.system.dao.jpa.UserRepository;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO extends BaseDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    public SysUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<SysUser> findAll() {
        return userRepository.findByDelFlag(ascByCreateTime, NOT_DEL);

    }

}

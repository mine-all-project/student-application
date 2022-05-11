package org.example.application.system.dao;

import org.example.application.common.BaseDAO;
import org.example.application.system.dao.jpa.UserRepository;
import org.example.application.system.entity.SystemUser;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAO extends BaseDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SystemUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public SystemUser save(SystemUser user) {
        return userRepository.save(user);
    }

    public SystemUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<SystemUser> findByIds(List<String> ids) {
        return userRepository.findByIdInAndStatusAndDelFlag(ids, 0, NOT_DEL);
    }

    @Transactional
    public void delUser(String id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public void unActiveUser(String id) {
        userRepository.unActiveUser(id);
    }

    @Transactional
    public void activeUser(String id) {
        userRepository.activeUser(id);
    }


    public List<SystemUser> findAll() {
        return userRepository.findByDelFlag(ascByCreateTime, NOT_DEL);
    }

    public List<SystemUser> getUserList(String userId, int status) {
        return userRepository.findByIdNotAndStatusAndDelFlag(userId, status, NOT_DEL);
    }
}

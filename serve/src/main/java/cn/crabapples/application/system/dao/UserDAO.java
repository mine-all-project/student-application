package cn.crabapples.application.system.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.system.dao.jpa.UserRepository;
import cn.crabapples.application.system.entity.SysUser;
import cn.crabapples.application.system.form.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    public SysUser save(UserForm form) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(form, user);
        return save(user);
    }

    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    public SysUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<SysUser> findByIds(List<String> ids) {
        return userRepository.findByIdInAndStatusAndDelFlag(ids, 0, NOT_DEL);
    }

    @Transactional
    public void delUser(String id) {
        userRepository.deleteById(id);
    }

    public List<SysUser> findByName(String name) {
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

    public SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag).orElse(null);
    }

    public List<SysUser> findAll() {
        return userRepository.findAll();

    }

    public List<SysUser> getUserList(String userId, int status) {
        return userRepository.findByIdNotAndStatusAndDelFlag(userId, status, NOT_DEL);
    }
}

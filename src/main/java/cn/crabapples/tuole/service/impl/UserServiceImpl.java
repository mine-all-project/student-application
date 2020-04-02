package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.dao.SysUserRepository;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;
import cn.crabapples.tuole.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * TODO 用户相关服务实现类
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private SysUserRepository sysUserRepository;

    public UserServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * 根据 [用户名] 查询用户
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public Optional<SysUser> findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser addUser(UserForm form) {
        return sysUserRepository.save(form.toEntity());
    }

    @Override
    public SysUser editUser(UserForm form) {
        SysUser user = sysUserRepository.findById(form.getId()).orElse(null);
        if (user != null) {
            return sysUserRepository.save(form.toEntity());
        }
        throw new ApplicationException("用户不存在");
    }

    @Override
    @Transactional
    public void delUser(String id) {
        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        sysUserRepository.delUser(id);
    }

    @Override
    public List<SysUser> findByName(String name) {
        return sysUserRepository.findByName(name);
    }

    @Override
    @Transactional
    public void unActiveUser(String id) {
        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        sysUserRepository.unActiveUser(id);
    }

    @Override
    @Transactional
    public void activeUser(String id) {
        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        sysUserRepository.activeUser(id);
    }

    @Override
    public List<SysUser> findAll() {
        logger.info("开始获取所有用户");
        return sysUserRepository.findAll();
    }

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     * @param username 用户名
     * @param password 密码
     * @param status 状态
     * @param delFlag 删除标记
     * @return 查询到的用户
     */
    @Override
    public Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return sysUserRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
    }


}

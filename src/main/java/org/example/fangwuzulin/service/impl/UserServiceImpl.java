package org.example.fangwuzulin.service.impl;

import org.example.fangwuzulin.config.ApplicationException;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;
import org.example.fangwuzulin.mapper.SysUserMapper;
import org.example.fangwuzulin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private SysUserMapper sysUserMapper;

    public UserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUser findByUsername(String username) {
        return null;
    }

    @Override
    public SysUser findByUsernameAndPassword(String username) {
        return null;
    }

    //    /**
//     * 根据 [用户名] 查询用户
//     * @param username 用户名
//     * @return 查询到的用户
//     */
//    @Override
//    public Optional<SysUser> findByUsername(String username) {
//        return sysUserRepository.findByUsername(username);
//    }

//    @Override
//    public SysUser addUser(UserForm form) {
//        return sysUserRepository.save(form.toEntity());
//    }
//
//    @Override
//    public SysUser editUser(UserForm form) {
//        SysUser user = sysUserRepository.findById(form.getId()).orElse(null);
//        if (user != null) {
//            return sysUserRepository.save(form.toEntity());
//        }
//        throw new ApplicationException("用户不存在");
//    }
//
//    @Override
//    @Transactional
//    public void delUser(String id) {
//        SysUser user = sysUserRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new ApplicationException("用户不存在");
//        }
//        sysUserRepository.delUser(id);
//    }
//
//    @Override
//    public List<SysUser> findByName(String name) {
//        return sysUserRepository.findByName(name);
//    }
//
//    @Override
//    @Transactional
//    public void unActiveUser(String id) {
//        SysUser user = sysUserRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new ApplicationException("用户不存在");
//        }
//        sysUserRepository.unActiveUser(id);
//    }
//
//    @Override
//    @Transactional
//    public void activeUser(String id) {
//        SysUser user = sysUserRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new ApplicationException("用户不存在");
//        }
//        sysUserRepository.activeUser(id);
//    }
//
//    @Override
//    public List<SysUser> findAll() {
//        logger.info("开始获取所有用户");
//        return sysUserRepository.findAll();
//    }
//
//    /**
//     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
//     * @param username 用户名
//     * @param password 密码
//     * @param status 状态
//     * @param delFlag 删除标记
//     * @return 查询到的用户
//     */
//    @Override
//    public Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
//        return sysUserRepository.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
//    }


}

package org.example.application.custom.service.impl;

import org.example.application.common.ApplicationException;
import org.example.application.common.config.ApplicationConfigure;
import org.example.application.common.utils.AssertUtils;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.UserDAO;
import org.example.application.custom.dto.SysUserDTO;
import org.example.application.custom.form.ResetPasswordForm;
import org.example.application.custom.form.TagListForm;
import org.example.application.custom.form.UserForm;
import org.example.application.custom.service.UserService;
import cn.hutool.crypto.digest.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 用户相关服务实现类
 *

 * 2020/1/27 2:10



 */
@Service
public class UserServiceImpl implements UserService {
    private final JwtConfigure jwtConfigure;
    private final String salt;
    @Value("${isDebug}")
    private boolean isDebug;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final org.example.application.custom.dao.UserDAO userDAO;

    public UserServiceImpl(ApplicationConfigure applicationConfigure,
                           JwtConfigure jwtConfigure, UserDAO userDAO) {
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
        this.salt = applicationConfigure.salt;
    }

    /**
     * 根据 [用户名] 查询用户
     *
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public org.example.application.custom.SysUser findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public org.example.application.custom.SysUser addUser(org.example.application.custom.form.UserForm form) {
        org.example.application.custom.SysUser entity = new org.example.application.custom.SysUser();
        BeanUtils.copyProperties(form, entity);
        return userDAO.save(entity);
    }

    @Override
    public org.example.application.custom.SysUser editUser(UserForm form) {
        org.example.application.custom.SysUser entity = userDAO.findById(form.getId());
        AssertUtils.notNull(entity, "用户不存在");
        BeanUtils.copyProperties(form, entity);
        return userDAO.save(entity);
    }

    @Override
    public void delUser(String id) {
        org.example.application.custom.SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setDelFlag(1);
        userDAO.save(user);
    }

    @Override
    public List<org.example.application.custom.SysUser> findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public void changeStatus(String id) {
        org.example.application.custom.SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setStatus(Math.abs(user.getStatus() - 1));
        userDAO.save(user);
    }

    @Override
    public List<org.example.application.custom.SysUser> findAll() {
        logger.info("开始获取所有用户");
        return userDAO.findAll();
    }

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @param status   状态
     * @param delFlag  删除标记
     * @return 查询到的用户
     */
    @Override
    public org.example.application.custom.SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userDAO.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
    }


    @Override
    public List<org.example.application.custom.dto.SysUserDTO> getUserListDTO(HttpServletRequest request) {
        String userId = getUserInfo(request, jwtConfigure, userDAO, isDebug).getId();
        List<org.example.application.custom.SysUser> userList = userDAO.getUserList(userId, 0);
        List<org.example.application.custom.dto.SysUserDTO> userDTOS = new ArrayList<>(userList.size());
        userList.forEach(e -> {
            org.example.application.custom.dto.SysUserDTO sysUserDTO = new SysUserDTO();
            BeanUtils.copyProperties(e, sysUserDTO);
            userDTOS.add(sysUserDTO);
        });
        return userDTOS;
    }

    @Override
    public org.example.application.custom.SysUser getUserInfo(HttpServletRequest request) {
        return getUserInfo(request, jwtConfigure, userDAO, isDebug);
    }

    @Override
    public void updateTags(HttpServletRequest request, TagListForm form) {
        org.example.application.custom.SysUser user = getUserInfo(request);
        userDAO.save(user);
    }

    @Override
    public void resetPassword(org.example.application.custom.form.ResetPasswordForm form) {
        String password = form.getPassword();
        String rePassword = form.getRePassword();
        if (!password.equals(rePassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        org.example.application.custom.SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        password = MD5.create().digestHex(form.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userDAO.save(user);
    }

    @Override
    public void updatePassword(ResetPasswordForm form) {
        String password = form.getPassword();
        String rePassword = form.getRePassword();
        if (!password.equals(rePassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        String oldPassword = MD5.create().digestHex(form.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (user.getPassword().equals(oldPassword)) {
            password = MD5.create().digestHex(password.getBytes(StandardCharsets.UTF_8));
            user.setPassword(password);
            userDAO.save(user);
            return;
        }
        throw new ApplicationException("密码错误");
    }
}

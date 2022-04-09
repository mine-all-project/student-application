package org.example.application.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import org.example.application.common.ApplicationException;
import org.example.application.common.DIC;
import org.example.application.common.utils.AssertUtils;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * TODO 用户相关服务实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private final JwtConfigure jwtConfigure;
    @Value("${isDebug}")
    private boolean isDebug;
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private final UserDAO userDAO;

    public SysUserServiceImpl(
            JwtConfigure jwtConfigure, UserDAO userDAO) {
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
    }

    /**
     * 根据 [用户名] 查询用户
     *
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public SysUser findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public SysUser addUser(UserForm form) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(form, entity);
        String password = MD5.create().digestHex(form.getPassword().getBytes(StandardCharsets.UTF_8));
        entity.setPassword(password);
        entity.setGoodsStatus(DIC.UN_AUTH);
        entity.setPaperStatus(DIC.UN_AUTH);
        return userDAO.save(entity);
    }

    @Override
    public SysUser editUser(UserForm form) {
        SysUser entity = userDAO.findById(form.getId());
        AssertUtils.notNull(entity, "用户不存在");
        BeanUtils.copyProperties(form, entity);
        return userDAO.save(entity);
    }

    @Override
    public void delUser(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setDelFlag(1);
        userDAO.save(user);
    }


    @Override
    public void changeStatus(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setStatus(Math.abs(user.getStatus() - 1));
        userDAO.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        logger.info("开始获取所有用户");
        return userDAO.findAll();
    }

    @Override
    public SysUser getUserInfo(HttpServletRequest request) {
        return getUserInfo(request, jwtConfigure, userDAO, isDebug);
    }


    @Override
    public void resetPassword(ResetPasswordForm form) {
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!newPassword.equals(againPassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        newPassword = MD5.create().digestHex(form.getNewPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(newPassword);
        userDAO.save(user);
    }

    @Override
    public void updatePassword(ResetPasswordForm form) {
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!newPassword.equals(againPassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        String oldPassword = MD5.create().digestHex(form.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (user.getPassword().equals(oldPassword)) {
            newPassword = MD5.create().digestHex(newPassword.getBytes(StandardCharsets.UTF_8));
            user.setPassword(newPassword);
            userDAO.save(user);
            return;
        }
        throw new ApplicationException("密码错误");
    }

    @Override
    public void updateUserInfo(UpdateUserInfoForm form) {
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        BeanUtils.copyProperties(form, user);
        userDAO.save(user);
    }

    @Override
    public void goodsCheckPass(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setGoodsStatus(DIC.AUTH);
        userDAO.save(user);
    }

    @Override
    public void goodsCheckFail(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setGoodsStatus(DIC.UN_AUTH);
        userDAO.save(user);
    }

    @Override
    public void paperCheckPass(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setPaperStatus(DIC.AUTH);
        userDAO.save(user);
    }

    @Override
    public void paperCheckFail(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setPaperStatus(DIC.UN_AUTH);
        userDAO.save(user);
    }
}

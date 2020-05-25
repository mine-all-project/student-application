package org.example.shiyanshi.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.shiyanshi.config.ApplicationConfigure;
import org.example.shiyanshi.config.ApplicationException;
import org.example.shiyanshi.dao.SysUserDAO;
import org.example.shiyanshi.config.base.ResponseDTO;
import org.example.shiyanshi.entity.SysUser;
import org.example.shiyanshi.form.ResetPasswordForm;
import org.example.shiyanshi.form.UserForm;
import org.example.shiyanshi.service.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private final String salt;
    private final SysUserDAO sysUserDAO;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, SysUserDAO sysUserDAO) {
        this.salt = applicationConfigure.SALT;
        this.sysUserDAO = sysUserDAO;
    }

    @Value("${isDebug}")
    private boolean isDebug;

    @Override
    public ResponseDTO login(UserForm form) {
        try {
            String username = form.getUsername();
            String password = new Md5Hash(form.getPassword(), salt).toString();
            logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            SysUser user = shiroCheckLogin(username, password);
            if (user == null) {
                return ResponseDTO.returnError("用户名或密码错");
            }
            return ResponseDTO.returnSuccess("登录成功", user);
        } catch (Exception e) {
            logger.warn("登录失败:[{}]", e.getMessage(), e);
            return ResponseDTO.returnError(e.getMessage());
        }
    }

    @Override
    public SysUser registry(Map<String, String> map) {
        SysUser exist = sysUserDAO.findByUsername(map.get("username"));
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
        String username = map.get("username");
        String password = new Md5Hash(map.get("password"), salt).toString();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(map.get("name"));
        sysUser.setPhone(map.get("phone"));
        sysUser.setMail(map.get("mail"));
        sysUser.setAdmin(false);
        sysUser.setStatus(0);
        return sysUserDAO.saveAndFlush(sysUser);
    }

    /**
     * shiro认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 认证成功后的用户对象
     */
    private SysUser shiroCheckLogin(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return (SysUser) subject.getPrincipal();
        } catch (IncorrectCredentialsException e) {
            logger.warn("shiro认证失败,密码错误:[{}]", e.getMessage());
        } catch (UnknownAccountException e) {
            logger.warn("shiro认证失败,用户不存在:[{}]", e.getMessage());
        } catch (Exception e) {
            logger.warn("shiro认证失败", e);
        }
        return null;
    }

    @Override
    public List<SysUser> getUserList() {
        return sysUserDAO.getAll();
    }

    @Override
    public SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        String username;
        if (user == null) {
            if (isDebug) {
                username = "admin";
            } else {
                throw new UnauthenticatedException("用户尚未登录");
            }
        } else {
            username = user.getUsername();
        }
        return sysUserDAO.findByUsername(username);
    }


    @Override
//    @RequiresPermissions("manage")
    public void changeStatus(String id) {
        SysUser sysUser = sysUserDAO.findById(id);
        assert sysUser != null;
        sysUser.setStatus(Math.abs(sysUser.getStatus() - 1));
        sysUserDAO.saveAndFlush(sysUser);
    }

    @Override
//    @RequiresPermissions("manage")
    public void removeUserById(String id) {
        SysUser sysUser = sysUserDAO.findById(id);
        assert sysUser != null;
        if (sysUser.isAdmin()) {
            throw new ApplicationException("不能删除管理员");
        }
        sysUserDAO.deleteById(sysUser.getId());
    }

    @Override
    public void saveUserInfo(SysUser sysUser) {
        sysUserDAO.saveAndFlush(sysUser);
    }

    @Override
    public void savePassword(Map map) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        String password = sysUser.getPassword();
        String oldPassword = new Md5Hash(map.get("password"), salt).toString();
        String newPassword = new Md5Hash(map.get("newPassword"), salt).toString();
        String rePassword = new Md5Hash(map.get("rePassword"), salt).toString();
        if (!newPassword.equals(rePassword)) {
            throw new ApplicationException("两次密码不相同");
        }
        if (!password.equals(oldPassword)) {
            throw new ApplicationException("原密码错误");
        }
        sysUser.setPassword(newPassword);
        sysUserDAO.saveAndFlush(sysUser);
    }

    @Override
    public SysUser getUsersById(String id) {
        SysUser user = sysUserDAO.findById(id);
        return user == null ? new SysUser() : user;

    }

    @Override
    public void resetPassword(ResetPasswordForm form) {
        String password = new Md5Hash(form.getPassword(), salt).toString();
        SysUser user = sysUserDAO.findById(form.getId());
        assert user != null;
        user.setPassword(password);
        sysUserDAO.saveAndFlush(user);
    }
}

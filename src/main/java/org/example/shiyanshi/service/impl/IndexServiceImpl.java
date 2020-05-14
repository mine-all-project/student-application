package org.example.shiyanshi.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.shiyanshi.config.ApplicationConfigure;
import org.example.shiyanshi.config.ApplicationException;
import org.example.shiyanshi.dao.SysUserDAO;
import org.example.shiyanshi.entity.SysUser;
import org.example.shiyanshi.form.UserForm;
import org.example.shiyanshi.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        String username = isDebug ? "user" : user.getUsername();
        return sysUserDAO.findByUsername(username);
    }

    @Value("${isDebug}")
    private boolean isDebug;
    private final SysUserDAO sysUserDAO;
    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    private final String salt;


    public IndexServiceImpl(ApplicationConfigure applicationConfigure, SysUserDAO sysUserDAO) {
        this.sysUserDAO = sysUserDAO;
        this.salt = applicationConfigure.SALT;
    }

    public boolean login(HttpServletRequest request, SysUser sysUser) {
        SysUser exist = sysUserDAO.findByUsernameAndPassword(sysUser.getUsername(), sysUser.getPassword());
        if (exist != null) {
            request.getSession().setAttribute("user", exist);
            return true;
        }
        return false;
    }

    @Override
    public SysUser loginOnShiro(UserForm form) {
        try {
            String username = form.getUsername();
            String password = new Md5Hash(form.getPassword(), salt).toString();
            logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            SysUser user = shiroCheckLogin(username, password);
            if (user == null) {
                throw new ApplicationException("用户名或密码错");
            }
            return user;
        } catch (Exception e) {
            logger.warn("登录失败:[{}]", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public SysUser registry(UserForm form) {
        SysUser exist = sysUserDAO.findByUsername(form.getUsername());
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
        String username = form.getUsername();
        String password = new Md5Hash(form.getPassword(), salt).toString();
        SysUser sysUser = new SysUser();
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(form.getName());
        sysUser.setPhone(form.getPhone());
        sysUser.setMail(form.getMail());
        sysUser.setAdmin(false);
        sysUser.setStatus(0);
        sysUserDAO.insertUser(sysUser);
        return sysUser;
    }

    @Override
    public void updateUserInfo(UserForm form) {
        SysUser user = getUserInfo();
        String password = new Md5Hash(form.getPassword(), salt).toString();
        if (user == null) {
            throw new ApplicationException("用户尚未登陆");
        }
        if (!user.getPassword().equals(password)) {
            throw new ApplicationException("原密码错误");
        }
        SysUser sysUser = form.toEntity();
        sysUserDAO.updateUserInfo(sysUser);
    }

    @Override
    public void addUser(UserForm form) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(form, user);
        user.setStatus(0);
        user.setAdmin(false);
        sysUserDAO.addUser(user);
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


}

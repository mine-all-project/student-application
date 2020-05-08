package org.example.fangwuzulin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.config.ApplicationConfigure;
import org.example.fangwuzulin.config.ApplicationException;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;
import org.example.fangwuzulin.mapping.SysUserMapping;
import org.example.fangwuzulin.service.IndexService;
import org.example.fangwuzulin.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class IndexServiceImpl implements IndexService {
    private final SysUserMapping sysUserMapping;
    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    private static final PasswordUtils passwordUtils = new PasswordUtils();

    public IndexServiceImpl(SysUserMapping sysUserMapping) {
        this.sysUserMapping = sysUserMapping;
    }

    public boolean login(HttpServletRequest request, SysUser sysUser) {
        SysUser exist = sysUserMapping.findByUsernameAndPassword(sysUser.getUsername(), sysUser.getPassword());
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
            String password = new PasswordUtils().md5Hash(form.getPassword());
            logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            SysUser user = shiroCheckLogin(username, password, form.getRandomCode());
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
        SysUser exist = sysUserMapping.findByUsername(form.getUsername());
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
        String username = form.getUsername();
        String password = passwordUtils.md5Hash(form.getPassword());
        SysUser sysUser = new SysUser();
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(form.getName());
        sysUser.setPhone(form.getPhone());
        sysUser.setMail(form.getMail());
        sysUser.set_admin(false);
        sysUser.setStatus(0);
        Integer count = sysUserMapping.insertUser(sysUser);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
        return sysUser;
    }

    @Override
    public void updateUserInfo(UserForm form) {
        SysUser user = getUserInfo();
        if (user == null) {
            throw new ApplicationException("用户尚未登陆");
        }
        form.setId(user.getId());
        user = sysUserMapping.findById(user.getId());
        if (form.getPassword() != null) {
            String password = passwordUtils.md5Hash(form.getPassword());
            if (!user.getPassword().equals(password)) {
                throw new ApplicationException("原密码错误");
            }
            SysUser sysUser = form.toEntity();
            String newPassword = passwordUtils.md5Hash(form.getNewPassword());
            sysUser.setPassword(newPassword);
            Integer count = sysUserMapping.updateUserInfo(sysUser);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        } else {
            Integer count = sysUserMapping.updateUserInfo(form.toEntity());
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        }
    }

    /**
     * shiro认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 认证成功后的用户对象
     */
    private SysUser shiroCheckLogin(String username, String password, String randomCode) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            String code = SecurityUtils.getSubject().getSession().getAttribute("randomCode").toString();
            System.err.println(code);
            System.err.println(randomCode);
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

package org.example.yaopin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.yaopin.config.ApplicationConfigure;
import org.example.yaopin.config.ApplicationException;
import org.example.yaopin.dao.DatabaseBakDAO;
import org.example.yaopin.dao.SysUserDAO;
import org.example.yaopin.dao.jpa.SysUserRepository;
import org.example.yaopin.dto.ResponseDTO;
import org.example.yaopin.entity.DatabaseBak;
import org.example.yaopin.entity.SysUser;
import org.example.yaopin.form.ResetPasswordForm;
import org.example.yaopin.form.UserForm;
import org.example.yaopin.service.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {
    private static final String CODE_KEY = "CODE:";
    private static final String CODE_TEMP = "CODE:TEMP:";
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String salt;
    private final SysUserDAO sysUserDAO;
    private final DatabaseBakDAO databaseBakDAO;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, SysUserDAO sysUserDAO, DatabaseBakDAO databaseBakDAO) {
        this.salt = applicationConfigure.SALT;
        this.sysUserDAO = sysUserDAO;
        this.databaseBakDAO = databaseBakDAO;
    }

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

    @Override
    public List<DatabaseBak> getDatabaseBakList() {
        return databaseBakDAO.getAll();
    }

    @Override
    public void addDatabaseBak() {
        try {
            String filePath = "d:/sqlbak/" + System.currentTimeMillis() + ".sql";
            databaseBak(filePath);
            DatabaseBak bak = new DatabaseBak();
            bak.setFilePath(filePath);
            databaseBakDAO.saveAndFlush(bak);
        } catch (Exception e) {
            logger.warn("数据库备份异常:", e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void delDatabaseBakById(String id) {
        try {
            String filePath = databaseBakDAO.findById(id).getFilePath();
            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    databaseBakDAO.deleteById(id);
                    return;
                }
            }
            throw new ApplicationException("备份删除失败");
        } catch (Exception e) {
            logger.warn("备份删除失败:", e);
            throw new ApplicationException(e.getMessage());

        }


    }

    @Override
    public void rollbackDatabaseBakById(String id) {
        DatabaseBak bak = databaseBakDAO.findById(id);
        if (bak == null) {
            throw new ApplicationException("备份信息异常");
        }
        try {
            String filePath = bak.getFilePath();
            databaseRollback(filePath);
        } catch (Exception e) {
            logger.warn("数据库还原异常:", e);
            throw new ApplicationException(e.getMessage());
        }
    }

    private void databaseBak(String filePath) throws IOException {
        String username = "root";
        String password = "root";
        String databaseName = "yaopin";
        String command = "cmd /c mysqldump -u " + username + " -p" + password + " " + databaseName + " > " + filePath;
        Process exec = Runtime.getRuntime().exec(command);
        System.err.println(command);
    }

    private void databaseRollback(String filePath) throws IOException {
        String username = "root";
        String password = "root";
        String databaseName = "yaopin";
        String command = "cmd /c mysql -u " + username + " -p" + password + " " + databaseName + " < " + filePath;
        Process exec = Runtime.getRuntime().exec(command);
        System.err.println(command);
    }

}

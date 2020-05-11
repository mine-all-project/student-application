package org.example.yaopin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.yaopin.config.ApplicationConfigure;
import org.example.yaopin.config.ApplicationException;
import org.example.yaopin.dao.jpa.SysUserRepository;
import org.example.yaopin.dto.ResponseDTO;
import org.example.yaopin.entity.SysUser;
import org.example.yaopin.form.UserForm;
import org.example.yaopin.service.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {
    private static final String CODE_KEY = "CODE:";
    private static final String CODE_TEMP = "CODE:TEMP:";
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String salt;
    private final SysUserRepository sysUserRepository;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, SysUserRepository sysUserRepository) {
        this.salt = applicationConfigure.SALT;
        this.sysUserRepository = sysUserRepository;
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
        SysUser exist = sysUserRepository.findByUsername(map.get("username")).orElse(null);
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
//        String redisKey = CODE_KEY + map.get("phone");
//        String code = redisTemplate.opsForValue().get(redisKey);
//        if (StringUtils.isEmpty(code)) {
//            throw new ApplicationException("验证码失效，请重新获取");
//        }
//        if (!code.equals(map.get("code"))) {
//            throw new ApplicationException("验证码错误");
//        }
        String username = map.get("username");
        String password = new Md5Hash(map.get("password"), salt).toString();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(map.get("name"));
        sysUser.setPhone(map.get("phone"));
        sysUser.setMail(map.get("mail"));
        sysUser.setAge(18);
        sysUser.setAdmin(false);
        sysUser.setStatus(0);
        return sysUserRepository.save(sysUser);
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
        return sysUserRepository.findAll();
    }

    @Override
//    @RequiresPermissions("manage")
    public void changeStatus(String id) {
        SysUser sysUser = sysUserRepository.findById(id).orElse(null);
        assert sysUser != null;
        sysUser.setStatus(Math.abs(sysUser.getStatus() - 1));
        sysUserRepository.save(sysUser);
    }

    @Override
//    @RequiresPermissions("manage")
    public void removeUserById(String id) {
        SysUser sysUser = sysUserRepository.findById(id).orElse(null);
        assert sysUser != null;
        if (sysUser.isAdmin()) {
            throw new ApplicationException("不能删除管理员");
        }
        sysUserRepository.delete(sysUser);
    }

    @Override
    public void saveUserInfo(SysUser sysUser) {
        sysUserRepository.saveAndFlush(sysUser);
    }

    @Override
    public void savePassword(Map map) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        String password = sysUser.getPassword();
        String oldPassword = new Md5Hash(map.get("password"), salt).toString();
        String newPassword = new Md5Hash(map.get("newPassword"), salt).toString();
        String rePassword = new Md5Hash(map.get("rePassword"), salt).toString();
        System.err.println(password);
        System.err.println(newPassword);
        System.err.println(rePassword);
        if (!newPassword.equals(rePassword)) {
            throw new ApplicationException("两次密码不相同");
        }
        if (!password.equals(oldPassword)) {
            throw new ApplicationException("原密码错误");
        }
        sysUser.setPassword(newPassword);
        sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser getUsersById(String id) {
        return sysUserRepository.findById(id).orElse(new SysUser());
    }
}

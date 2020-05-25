package org.example.tuole.service.impl;

import org.example.tuole.config.ApplicationConfigure;
import org.example.tuole.config.ApplicationException;
import org.example.tuole.config.MailUtils;
import org.example.tuole.dao.SysUserRepository;
import org.example.tuole.dto.ResponseDTO;
import org.example.tuole.entity.SysUser;
import org.example.tuole.form.UserForm;
import org.example.tuole.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SysServiceImpl implements SysService {
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private final String salt;
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
        } catch (Exception e) {
            logger.warn("登录失败:[{}]", e.getMessage(), e);
            return ResponseDTO.returnError(e.getMessage());
        }
        return ResponseDTO.returnSuccess("登录成功");
    }

    @Override
    public SysUser registry(HttpServletRequest request, Map<String, String> map) {
        SysUser exist = sysUserRepository.findByUsername(map.get("username")).orElse(null);
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
        String code = String.valueOf(request.getSession().getAttribute("randomCode"));
        if (StringUtils.isEmpty(code)) {
            throw new ApplicationException("验证码失效，请重新获取");
        }
        if (!code.equals(map.get("code"))) {
            throw new ApplicationException("验证码错误");
        }
        String username = map.get("username");
        String password = new Md5Hash(map.get("password"), salt).toString();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName(map.get("name"));
        sysUser.setPhone(map.get("phone"));
        sysUser.setMail(map.get("mail"));
        sysUser.setAge(18);
        sysUser.setIsAdmin(0);
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


}


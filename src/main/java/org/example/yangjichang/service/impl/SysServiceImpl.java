package org.example.yangjichang.service.impl;

import org.example.yangjichang.config.ApplicationConfigure;
import org.example.yangjichang.config.ApplicationException;
import org.example.yangjichang.config.MailUtils;
import org.example.yangjichang.config.SmsUtils;
import org.example.yangjichang.dao.SysUserRepository;
import org.example.yangjichang.dto.ResponseDTO;
import org.example.yangjichang.entity.SysUser;
import org.example.yangjichang.form.UserForm;
import org.example.yangjichang.service.SysService;
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

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO 系统相关服务实现类
 *
 * @author Mr.He
 * 2020/1/28 23:23
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
public class SysServiceImpl implements SysService {
    private static final String CODE_KEY = "CODE:";
    private static final String CODE_TEMP = "CODE:TEMP:";
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String salt;
    private final StringRedisTemplate redisTemplate;
    private final SmsUtils smsUtils;
    private final SysUserRepository sysUserRepository;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, StringRedisTemplate redisTemplate, SmsUtils smsUtils, SysUserRepository sysUserRepository) {
        this.salt = applicationConfigure.SALT;
        this.redisTemplate = redisTemplate;
        this.smsUtils = smsUtils;
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
    public SysUser registry(Map<String, String> map) {
        SysUser exist = sysUserRepository.findByUsername(map.get("username")).orElse(null);
        if (exist != null) {
            throw new ApplicationException("用户名已经存在");
        }
        String redisKey = CODE_KEY + map.get("phone");
        String code = redisTemplate.opsForValue().get(redisKey);
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
    public void sendCodeByMail(String mail) {
        try {
            String redisKey = CODE_TEMP + mail;
            Long time = redisTemplate.opsForValue().getOperations().getExpire(redisKey);
            if (time > 0) {
                throw new ApplicationException("请" + time + "秒后再重新获取");
            }
            String code = createCheckCode(mail);
            String title = "验证邮件";
            String content = String.format("您的验证码为 [%s]", code);
            logger.info("本次验证码发送至:[{}],验证码为:[{}]", mail, code);
            MailUtils.sendMail(title, content, mail);
            redisTemplate.opsForValue().set(redisKey, code, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.warn("邮件发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void sendCodeByPhone(String phone) {
        try {
            String redisKey = CODE_TEMP + phone;
            Long time = redisTemplate.opsForValue().getOperations().getExpire(redisKey);
            if (time > 0) {
                throw new ApplicationException("请" + time + "秒后再重新获取");
            }
            String code = createCheckCode(phone);
            logger.info("本次验证码发送至:[{}],验证码为:[{}]", phone, code);
            smsUtils.sendCodeMessage(phone, code);
            redisTemplate.opsForValue().set(redisKey, code, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.warn("短信发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    private String createCheckCode(String userKey) {
        String redisKey = CODE_KEY + userKey;
        String code = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(code)) {
            StringBuilder newCode = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                newCode.append(random.nextInt(10));
            }
            code = newCode.toString();
            redisTemplate.opsForValue().set(redisKey, code, 10, TimeUnit.MINUTES);
        }
        return code;
    }

}


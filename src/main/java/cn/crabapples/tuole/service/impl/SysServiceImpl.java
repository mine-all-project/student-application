package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationConfigure;
import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.config.MailUtils;
import cn.crabapples.tuole.config.SmsUtils;
import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;
import cn.crabapples.tuole.service.SysService;
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

import java.util.UUID;
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
    private static final String CODE_KEY = "code:";
    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String salt;
    private final StringRedisTemplate redisTemplate;
    private final SmsUtils smsUtils;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, StringRedisTemplate redisTemplate, SmsUtils smsUtils) {
        this.salt = applicationConfigure.SALT;
        this.redisTemplate = redisTemplate;
        this.smsUtils = smsUtils;
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
            String code = createCheckCode(mail);
            String title = "验证邮件";
            String content = String.format("您的验证码为 [%s]", code);
            logger.info("本次验证码发送至:[{}],验证码为:[{}]", mail, code);
            MailUtils.sendMail(title, content, mail);
        } catch (Exception e) {
            logger.warn("邮件发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException("验证码发送异常");
        }
    }

    @Override
    public void sendCodeByPhone(String phone) {
        try {
            String code = createCheckCode(phone);
            logger.info("本次验证码发送至:[{}],验证码为:[{}]", phone, code);
            smsUtils.sendCodeMessage(phone, code);
        } catch (Exception e) {
            logger.warn("短信发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException("验证码发送异常");
        }
    }

    private String createCheckCode(String userKey) {
        String redisKey = CODE_KEY + userKey;
        String code = redisTemplate.opsForValue().get(redisKey);
        if (null == code || "".equals(code)) {
            code = UUID.randomUUID().toString().substring(0, 5);
            redisTemplate.opsForValue().set(redisKey, code, 10, TimeUnit.MINUTES);
        }
        return code;
    }

}


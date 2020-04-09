package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationConfigure;
import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.config.MailUtils;
import cn.crabapples.tuole.config.MailUtilsConfigure;
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
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.crabapples.tuole.config.MailUtils.initMessage;

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

    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String salt;


    public SysServiceImpl(ApplicationConfigure applicationConfigure) {
        this.salt = applicationConfigure.SALT;
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
    public Map<String, String> sendCodeByMail(String mail) {
        try {
            String code = UUID.randomUUID().toString().substring(0, 5);
            String title = "验证邮件";
            String content = "您的验证码为:" + code;
            MailUtilsConfigure configure = new MailUtilsConfigure();
            configure.setDebug(true);
            Session session = MailUtils.initSession(configure);
            MimeMessage message = initMessage(configure, session, title, mail);
            Multipart multipart = new MimeMultipart();
            MailUtils.addMailContentText(multipart, content);
            assert message != null;
            message.setContent(multipart);
            message.saveChanges();
            MailUtils.sendEmail(configure, session, message);
            Map<String, String> result = new HashMap<>(1);
            result.put("code", code);
            return result;
        } catch (Exception e) {
            logger.warn("邮件发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException("验证码发送异常");
        }
    }

    @Override
    public Map<String, String> getCodeByPhone(String phone) {
        return null;
    }
}

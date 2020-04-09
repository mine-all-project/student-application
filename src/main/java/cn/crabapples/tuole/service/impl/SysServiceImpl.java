package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationConfigure;
import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.config.MailUtils;
import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;
import cn.crabapples.tuole.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private final RestTemplate restTemplate;


    public SysServiceImpl(ApplicationConfigure applicationConfigure, RestTemplate restTemplate) {
        this.salt = applicationConfigure.SALT;
        this.restTemplate = restTemplate;
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
            MailUtils.sendMail(title, content, mail);
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
        try {
            String code = UUID.randomUUID().toString().substring(0, 5);
            String title = "验证邮件";
            String content = "您的验证码为:" + code;
//            MailUtils.sendMail(title, content, phone);
            Map<String, String> result = new HashMap<>(1);
            result.put("code", code);
            return result;
        } catch (Exception e) {
            logger.warn("邮件发送异常:[{}]", e.getMessage(), e);
            throw new ApplicationException("验证码发送异常");
        }
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDateTime.format(formatter);
        String appId = "1400344819";
        String appKey = "afd76be81dc804a1d7dfa05a74affa68";
        String signId = "275223";
        String secretId = "AKIDpW26yaV5A0ggy46FOWNxhHeqUsmkwP35";
        String templateId = "568575";
        String Signature = "568575";

        StringBuffer authBuilder = new StringBuffer("TC3-HMAC-SHA256");
        authBuilder.append(" ").append("Credential").append("=")
                .append(secretId).append("/").append(date).append("/sms/tc3_request")
                .append(" ,SignedHeaders=content-type;host, ").append("Signature=").append("SignedHeaders=");
        System.err.println(authBuilder);


//        Authorization: TC3-HMAC-SHA256 Credential=AKIDz8krbsJ5yKBZQpn74WFkmLPx3EXAMPLE/2018-10-09/cvm/tc3_request
//       , SignedHeaders=content-type;host, Signature=5da7a33f6993f0614b047e5df4582db9e9bf4672ba50567dba16c6ccf174c474


//                "TC3-HMAC-SHA256 Credential=" +secretId+"/"+date+"/sms/tc3_request";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-TC-Action", "SendSms");
        httpHeaders.add("X-TC-Timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        httpHeaders.add("X-TC-Version", "2019-07-11");
        httpHeaders.add("Authorization", authBuilder.toString());
        Map<String, String> map = new HashMap<>();
        map.put("Action", "SendSms");
        map.put("Version", "2019-07-11");
        map.put("PhoneNumberSet.0", "+8613765710705");
        map.put("TemplateID", templateId);
        map.put("SmsSdkAppid", appId);
        map.put("Sign", "枕竹知雨韵");
        map.put("TemplateParamSet.0", "66666");
        StringBuffer stringBuffer = new StringBuffer("https://sms.tencentcloudapi.com?");
        stringBuffer.append("Action").append("=").append("SendSms").append("&")
                .append("Version").append("=").append("2019-07-11").append("&")
                .append("PhoneNumberSet.0").append("=").append("13765710705").append("&")
                .append("TemplateID").append("=").append(templateId).append("&")
                .append("SmsSdkAppid").append("=").append(appId).append("&")
                .append("Sign").append("=").append("枕竹知雨韵").append("&")
                .append("TemplateParamSet.0").append("=").append("666666");
        RestTemplate restTemplate = new RestTemplate();
        System.err.println(restTemplate);
        System.err.println(stringBuffer.toString());
        String result = restTemplate.getForObject(stringBuffer.toString(), String.class);
        System.err.println(result);
    }
//    https://cvm.tencentcloudapi.com/?Action=DescribeInstances&
//   Version=2017-03-12&SignatureMethod=HmacSHA256&Timestamp=1527672334&Signature=37ac2f4fde00b0ac9bd9eadeb459b1bbee224158d66e7ae5fcadb70b2d181d02&Region=ap-guangzhou&Nonce=23823223&SecretId=AKIDEXAMPLE
//    https://sms.tencentcloudapi.com/?Action=SendSms&
//   Version=2019-07-11&PhoneNumberSet.0=13765710705&TemplateID=568575&SmsSdkAppid=1400344819&Sign=%E6%9E%95%E7%AB%B9%E7%9F%A5%E9%9B%A8%E9%9F%B5&TemplateParamSet.0=666666
}


package org.example.textreader.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.textreader.common.ApplicationException;
import org.example.textreader.common.ResponseDTO;
import org.example.textreader.system.dao.SysUserRepository;
import org.example.textreader.system.entity.SysUser;
import org.example.textreader.system.form.UserForm;
import org.example.textreader.system.service.SysService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@Slf4j
public class SystemServiceImpl implements SysService {
    private final SysUserRepository sysUserRepository;


    public SystemServiceImpl( SysUserRepository sysUserRepository) {

        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public ResponseDTO login(UserForm form) {
        try {
            String username = form.getUsername();
            String password = new Md5Hash(form.getPassword()).toString();
            log.info("开始登录->用户名:[{}],密码:[{}]", username, password);
            SysUser user = shiroCheckLogin(username, password);
            if (user == null) {
                return ResponseDTO.returnError("用户名或密码错");
            }
        } catch (Exception e) {
            log.warn("登录失败:[{}]", e.getMessage(), e);
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
//        String code = String.valueOf(request.getSession().getAttribute("randomCode"));
//        if (StringUtils.isEmpty(code)) {
//            throw new ApplicationException("验证码失效，请重新获取");
//        }
//        if (!code.equals(map.get("code"))) {
//            throw new ApplicationException("验证码错误");
//        }
        String username = map.get("username");
        String password = new Md5Hash(map.get("password")).toString();
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
            log.warn("shiro认证失败,密码错误:[{}]", e.getMessage());
        } catch (UnknownAccountException e) {
            log.warn("shiro认证失败,用户不存在:[{}]", e.getMessage());
        } catch (Exception e) {
            log.warn("shiro认证失败", e);
        }
        return null;
    }


}


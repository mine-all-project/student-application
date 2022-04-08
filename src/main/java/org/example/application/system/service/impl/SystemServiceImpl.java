package org.example.application.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import org.example.application.common.ApplicationException;
import org.example.application.common.DIC;
import org.example.application.common.utils.AssertUtils;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.common.utils.jwt.JwtTokenUtils;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysUserService;
import org.example.application.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;


/**
 * TODO 系统相关服务实现类
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
    @Value("${isDebug}")
    private boolean isDebug;

    private final SysUserService sysUserService;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;


    public SystemServiceImpl(SysUserService sysUserService, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.sysUserService = sysUserService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public String loginCheck(UserForm form) {
        String username = form.getUsername();
        String password = MD5.create().digestHex(form.getPassword().getBytes(StandardCharsets.UTF_8));
        logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
        SysUser sysUser = sysUserService.findByUsername(username);
        AssertUtils.notNull(sysUser, "用户名不存在");
        if (sysUser.getStatus() == DIC.USER_LOCK) {
            throw new ApplicationException("账户已被禁用，请联系管理员");
        }
        if (sysUser.getPassword().equals(password)) {
            return JwtTokenUtils.createJWT(sysUser.getId(), sysUser.getUsername(), jwtConfigure);
        }
        throw new ApplicationException("密码错误");
    }

    @Override
    public SysUser getUserInfo(HttpServletRequest request) {
        return sysUserService.getUserInfo(request);
    }

    @Override
    public SysUser registry(UserForm form) {
        form.setRole(2);
        return sysUserService.addUser(form);
    }
}

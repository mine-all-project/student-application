package org.example.waimai.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.waimai.config.ApplicationConfigure;
import org.example.waimai.config.ApplicationException;
import org.example.waimai.entity.SysUser;
import org.example.waimai.mapping.SysUserMapping;
import org.example.waimai.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {
    @Value("${isDebug}")
    private boolean isDebug;

    private static final Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);
    private final String salt;

    private final SysUserMapping sysUserMapping;

    public ManageServiceImpl(ApplicationConfigure applicationConfigure, SysUserMapping sysUserMapping) {
        this.salt = applicationConfigure.SALT;
        this.sysUserMapping = sysUserMapping;
    }

    @Override
    public List<SysUser> getUserList() {
        return sysUserMapping.findAllUser();
    }

    @Override
    public void changeStatus(String id) {
        SysUser sysUser = sysUserMapping.findById(id);
        assert sysUser != null;
        if (sysUser.isAdmin()) {
            throw new ApplicationException("不能对管理员操作");
        }
        sysUser.setStatus(Math.abs(sysUser.getStatus() - 1));
        Integer count = sysUserMapping.updateUserInfo(sysUser);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public void removeUserById(String id) {
        SysUser sysUser = sysUserMapping.findById(id);
        assert sysUser != null;
        if (sysUser.isAdmin()) {
            throw new ApplicationException("不能删除管理员");
        }
        sysUserMapping.removeUserById(id);
    }

    @Override
    public void changePassword(Map map) {
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
        sysUserMapping.updateUserInfo(sysUser);
    }
}

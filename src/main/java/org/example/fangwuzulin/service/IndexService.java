package org.example.fangwuzulin.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.mapping.SysUserMapping;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class IndexService {
    private final SysUserMapping sysUserMapping;

    public IndexService(SysUserMapping sysUserMapping) {
        this.sysUserMapping = sysUserMapping;
    }

    public boolean login(HttpServletRequest request, SysUser sysUser) {
        SysUser sysUser1 = sysUserMapping.findByUsernameAndPassword(sysUser.getUsername(), sysUser.getPassword());
        System.out.println(sysUser);
        if (sysUser1 != null) {
            System.err.println("登陆成功");
            request.getSession().setAttribute("user", sysUser1);
            return true;
        }
        System.err.println("登陆失败");
        return false;
    }

    public SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}

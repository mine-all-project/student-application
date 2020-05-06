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
        SysUser exist = sysUserMapping.findByUsernameAndPassword(sysUser.getUsername(), sysUser.getPassword());
        if (exist != null) {
            request.getSession().setAttribute("user", exist);
            return true;
        }
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

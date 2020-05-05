package org.example.fangwuzulin.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.mapper.SysUserMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class IndexService {
    private final SysUserMapper sysUserMapper;

    public IndexService(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    public boolean login(HttpServletRequest request, SysUser sysUser) {
        SysUser sysUser1 = sysUserMapper.findByUsernameAndPassword(sysUser.getUsername(), sysUser.getPassword());
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

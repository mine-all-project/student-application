package org.example.fangwuzulin.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.dao.SysUserRepository;
import org.example.fangwuzulin.entity.SysUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class IndexService {
    private final SysUserRepository sysUserRepository;

    public IndexService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public boolean login(HttpServletRequest request, SysUser sysUser) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<SysUser> example = Example.of(sysUser, exampleMatcher);
        Optional<SysUser> optional = sysUserRepository.findOne(example);
        System.out.println(sysUser);
        if (optional.isPresent()) {
            System.err.println("登陆成功");
            request.getSession().setAttribute("user", optional.get());
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

package org.example.fangwuzulin.service;

import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.dto.RandomCode;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface IndexService {

    default SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }

    default void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);


}

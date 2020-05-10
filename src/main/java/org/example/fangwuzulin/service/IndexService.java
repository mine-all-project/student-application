package org.example.fangwuzulin.service;

import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface IndexService extends BaseService{
    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);


}

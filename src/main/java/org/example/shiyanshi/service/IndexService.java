package org.example.shiyanshi.service;

import org.example.shiyanshi.entity.SysUser;
import org.example.shiyanshi.form.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface IndexService extends BaseService{

    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);

    void addUser(UserForm form);
}

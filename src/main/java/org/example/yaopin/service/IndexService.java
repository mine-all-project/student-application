package org.example.yaopin.service;

import org.example.yaopin.entity.SysUser;
import org.example.yaopin.form.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface IndexService extends BaseService{

    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);


}

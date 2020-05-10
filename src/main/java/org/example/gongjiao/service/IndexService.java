package org.example.gongjiao.service;

import org.example.gongjiao.entity.SysUser;
import org.example.gongjiao.form.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface IndexService extends BaseService{

    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);


}

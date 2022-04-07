package org.example.application.system.service;

import org.example.application.system.entity.SysUser;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;


public interface SystemService {
    String loginCheck(UserForm form);

    SysUser getUserInfo(HttpServletRequest request);
}

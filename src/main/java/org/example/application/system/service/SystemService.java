package org.example.application.system.service;

import org.example.application.system.entity.SystemUser;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;


public interface SystemService {
    String loginCheck(UserForm form);

    SystemUser getUserInfo(HttpServletRequest request);

    SystemUser registry(UserForm form);
}

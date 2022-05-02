package org.example.application.system.service;

import org.example.application.system.form.UserForm;


public interface SysService {
    String loginCheck(UserForm form);

    void findPwd(UserForm form);

    void resetPwd(UserForm form);
}

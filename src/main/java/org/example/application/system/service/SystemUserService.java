package org.example.application.system.service;

import org.example.application.common.BaseService;
import org.example.application.system.entity.SystemUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * TODO 用户相关服务
 */
public interface SystemUserService extends BaseService {

    SystemUser findByUsername(String username);

    SystemUser addUser(UserForm form);

    SystemUser editUser(UserForm form);

    void delUser(String id);

    void changeStatus(String id);

    List<SystemUser> findAll();

    SystemUser getUserInfo(HttpServletRequest request);

    void resetPassword(ResetPasswordForm form);

    void updatePassword(ResetPasswordForm form);

    void updateUserInfo(UpdateUserInfoForm form);

    void goodsCheckPass(String id);

    void goodsCheckFail(String id);

    void paperCheckPass(String id);

    void paperCheckFail(String id);
}

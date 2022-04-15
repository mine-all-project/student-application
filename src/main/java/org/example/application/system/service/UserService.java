package org.example.application.system.service;

import org.example.application.common.BaseService;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * TODO 用户相关服务
 */
public interface UserService extends BaseService {

    SysUser updateName(HttpServletRequest request,String name);

    SysUser updatePassword(HttpServletRequest request,ResetPasswordForm form);

    SysUser findByUsername(String username);

    SysUser addUser(UserForm form);

    SysUser editUser(UserForm form);

    void delUser(String id);

    void changeStatus(String id);

    List<SysUser> findAll();

    SysUser getUserInfo(HttpServletRequest request);

    void resetPassword(ResetPasswordForm form);

    void updateUserInfo(UpdateUserInfoForm form);

}

package org.example.application.system.service;

import org.example.application.common.BaseService;
import org.example.application.system.dto.SysUserDTO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * TODO 用户相关服务
 *

 * 2020/1/27 2:10



 */
public interface UserService extends BaseService {

    /**
     * 根据 [用户名] 查询用户
     *
     * @param username 用户名
     * @return 查询到的用户
     */
    SysUser findByUsername(String username);

    /**
     * 添加用户
     */
    SysUser addUser(UserForm form);

    /**
     * 修改用户
     */
    SysUser editUser(UserForm form);

    /**
     * 删除用户
     */
    void delUser(String id);

    /**
     * 根据 [姓名] 查找用户
     *
     * @param name 姓名
     * @return 查找到的用户列表
     */
    List<SysUser> findByName(String name);

    /**
     * 修改用户状态
     *
     * @param id 用户ID
     */
    void changeStatus(String id);


    /**
     * 根据 [用户名] [密码] [状态] [删除标记] 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @param status   状态
     * @param delFlag  删除标记
     * @return 查询到的用户
     */
    SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);

    List<SysUser> findAll();


    List<SysUserDTO> getUserListDTO(HttpServletRequest request);

    SysUser getUserInfo(HttpServletRequest request);

    void resetPassword(ResetPasswordForm form);

    void updatePassword(ResetPasswordForm form);

    void updateUserInfo(UpdateUserInfoForm form);

    void publishCheckPass(String id);

    void publishCheckFail(String id);

    void talkCheckPass(String id);

    void talkCheckFailTalk(String id);

    SysUser getUserById(String id);
}

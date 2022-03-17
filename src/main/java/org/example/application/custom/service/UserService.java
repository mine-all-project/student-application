package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.dto.SysUserDTO;
import org.example.application.custom.form.ResetPasswordForm;
import org.example.application.custom.form.TagListForm;
import org.example.application.custom.form.UserForm;

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
    org.example.application.custom.SysUser findByUsername(String username);

    /**
     * 添加用户
     */
    org.example.application.custom.SysUser addUser(org.example.application.custom.form.UserForm form);

    /**
     * 修改用户
     */
    org.example.application.custom.SysUser editUser(UserForm form);

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
    List<org.example.application.custom.SysUser> findByName(String name);

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
    org.example.application.custom.SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);

    List<org.example.application.custom.SysUser> findAll();


    List<SysUserDTO> getUserListDTO(HttpServletRequest request);

    SysUser getUserInfo(HttpServletRequest request);

    void updateTags(HttpServletRequest request, TagListForm form);

    void resetPassword(org.example.application.custom.form.ResetPasswordForm form);

    void updatePassword(ResetPasswordForm form);
}

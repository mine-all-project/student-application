package org.example.application.system.service;

import org.example.application.system.entity.SysMenu;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO 系统相关服务
 *

 * 2020/1/28 23:22



 */
public interface SysService {
    /**
     * 用户登录
     * @return 登录成功后返回token
     */
    String loginCheck(UserForm form);

    /**
     * 获取当前用户系统菜单
     * @return 当前用户拥有的菜单
     */
    List<SysMenu> getSysMenus(HttpServletRequest request);

    SysUser registry(UserForm form);
}

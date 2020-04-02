package cn.crabapples.tuole.service;


import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysMenu;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;

import java.util.List;

/**
 * TODO 系统相关服务
 *
 * @author Mr.He
 * 2020/1/28 23:22
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface SysService {
    /**
     * 用户登录
     * @return 登录成功后返回的登录信息
     */
    ResponseDTO login(UserForm form);

    /**
     * 获取系统菜单
     * @return
     */
    List<SysMenu> getSysMenus(SysUser user);
}

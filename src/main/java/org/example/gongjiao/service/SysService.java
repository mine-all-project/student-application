package org.example.gongjiao.service;


import org.example.gongjiao.dto.ResponseDTO;
import org.example.gongjiao.entity.SysUser;
import org.example.gongjiao.form.UserForm;

import java.util.List;
import java.util.Map;

public interface SysService {
    /**
     * 用户登录
     * @return 登录成功后返回的登录信息
     */
    ResponseDTO login(UserForm form);

    SysUser registry(Map<String,String> map);

    List<SysUser> getUserList();

    void changeStatus(String id);

    void removeUserById(String id);

    void saveUserInfo(SysUser sysUser);

    void savePassword(Map map);

    SysUser getUsersById(String id);
}

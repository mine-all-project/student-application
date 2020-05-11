package org.example.yaopin.service;


import org.example.yaopin.dto.ResponseDTO;
import org.example.yaopin.entity.SysUser;
import org.example.yaopin.form.UserForm;

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

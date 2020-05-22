package org.example.shiyanshi.service;


import org.example.shiyanshi.config.base.ResponseDTO;
import org.example.shiyanshi.entity.DatabaseBak;
import org.example.shiyanshi.entity.SysUser;
import org.example.shiyanshi.form.ResetPasswordForm;
import org.example.shiyanshi.form.UserForm;

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

    void resetPassword(ResetPasswordForm form);

    List<DatabaseBak> getDatabaseBakList();

    void addDatabaseBak();

    void delDatabaseBakById(String id);

    void rollbackDatabaseBakById(String id);
}

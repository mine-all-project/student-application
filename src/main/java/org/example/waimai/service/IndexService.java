package org.example.waimai.service;

import org.example.waimai.entity.SysUser;
import org.example.waimai.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IndexService extends BaseService {
    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(HttpServletRequest request, UserForm form);

    SysUser getUserInfo();

    SysUser registry(UserForm form);

    List<Map<String, Object>> getProvincesList();

    List<Map<String, Object>> getCitiesList(String pid);

    List<Map<String, Object>> getAreasList(String pid);

    void updateUserInfo(UserForm form);

    void changeUserInfo(UserForm form);

    SysUser getUsersById(String id);


}

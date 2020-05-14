package org.example.fangwuzulin.service;

import com.alibaba.fastjson.JSONObject;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IndexService extends BaseService{
    boolean login(HttpServletRequest request, SysUser sysUser);

    SysUser loginOnShiro(HttpServletRequest request,UserForm form);

    SysUser registry(UserForm form);

    void updateUserInfo(UserForm form);

    void changeUserInfo(UserForm form);

    SysUser getUsersById(String id);

    List<Map<String,Object>> getProvincesList();

    List<Map<String,Object>> getCitiesList(String pid);

    List<Map<String,Object>> getAreasList(String pid);

}

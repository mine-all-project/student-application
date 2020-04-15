package org.example.yangjichang.service;


import org.example.yangjichang.dto.ResponseDTO;
import org.example.yangjichang.entity.SysUser;
import org.example.yangjichang.form.UserForm;

import java.util.List;
import java.util.Map;

public interface SysService {
    /**
     * 用户登录
     * @return 登录成功后返回的登录信息
     */
    ResponseDTO login(UserForm form);

    void sendCodeByMail(String mail);

    void sendCodeByPhone(String phone);

    SysUser registry(Map<String,String> map);

    List<SysUser> getUserList();
}

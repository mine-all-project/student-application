package org.example.tuole.service;


import org.example.tuole.dto.ResponseDTO;
import org.example.tuole.entity.SysUser;
import org.example.tuole.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SysService {
    /**
     * 用户登录
     *
     * @return 登录成功后返回的登录信息
     */
    ResponseDTO login(UserForm form);

    SysUser registry(HttpServletRequest request, Map<String, String> map);
}
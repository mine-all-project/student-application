package org.example.yangjichang.service;


import org.example.yangjichang.dto.ResponseDTO;
import org.example.yangjichang.entity.SysUser;
import org.example.yangjichang.form.UserForm;

import java.util.Map;

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

    void sendCodeByMail(String mail);

    void sendCodeByPhone(String phone);

    SysUser registry(Map<String,String> map);
}

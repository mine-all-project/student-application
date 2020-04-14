package cn.crabapples.tuole.service;


import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;

import java.util.Map;

/**
 * TODO 系统相关服务
 *
 *
 * 2020/1/28 23:22
 *
 *
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

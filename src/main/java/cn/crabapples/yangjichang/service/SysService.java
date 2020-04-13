package cn.crabapples.yangjichang.service;


import cn.crabapples.yangjichang.dto.ResponseDTO;
import cn.crabapples.yangjichang.entity.SysUser;
import cn.crabapples.yangjichang.form.UserForm;

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

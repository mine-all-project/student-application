package org.example.application.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统相关接口
 */

@RestController
@Slf4j
@RequestMapping("/api")
public class SysController extends BaseController {

    private final SysService sysService;

    public SysController(SysService sysService) {
        this.sysService = sysService;
    }

    @JwtIgnore
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, Groups.IsLogin.class);
        String token = sysService.loginCheck(form);
        log.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }

    @PostMapping("/logout")
    public ResponseDTO logout() {
        log.info("收到请求->退出登陆");
//        sysService.logout();
        log.info("返回结果->退出登录结束");
        return ResponseDTO.returnSuccess();
    }


}

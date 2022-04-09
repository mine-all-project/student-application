package org.example.application.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsAdd;
import org.example.application.common.groups.IsLogin;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
        super.validator(form, IsLogin.class);
        String token = sysService.loginCheck(form);
        log.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }

    @JwtIgnore
    @PostMapping("/logout")
    public ResponseDTO logout() {
        log.info("收到请求->退出登陆");
//        sysService.logout();
        log.info("返回结果->退出登录结束");
        return ResponseDTO.returnSuccess();
    }

    @JwtIgnore
    @PostMapping("/registry")
    public ResponseDTO registry(@RequestBody UserForm form) {
        log.info("收到请求->注册");
        super.validator(form, IsAdd.class);
        SysUser user = sysService.registry(form);
        log.info("返回结果->注册结束:[{}]", user);
        return ResponseDTO.returnSuccess(user);
    }

    @GetMapping("/getUserInfo")
    public ResponseDTO getUserInfo(HttpServletRequest request) {
        log.info("收到请求->获取当前用户信息");
        SysUser sysUser = sysService.getUserInfo(request);
        log.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

}

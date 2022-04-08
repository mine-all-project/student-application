package org.example.application.system.controller;

import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 系统相关接口
 */

@RestController
@RequestMapping("/api")
public class SystemController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SystemController.class);
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/getUserInfo")
    public ResponseDTO getUserInfo(HttpServletRequest request) {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = systemService.getUserInfo(request);
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @JwtIgnore
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserForm form) {
        logger.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, Groups.IsLogin.class);
        String token = systemService.loginCheck(form);
        logger.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }

    @JwtIgnore
    @PostMapping("/registry")
    public ResponseDTO registry(@RequestBody UserForm form) {
        logger.info("收到请求->用户注册:[{}]", form);
        super.validator(form, Groups.IsAdd.class);
        SysUser user = systemService.registry(form);
        logger.info("返回结果->注册结束[{}]", user);
        return ResponseDTO.returnSuccess("注册成功", user);
    }

    @PostMapping("/logout")
    public ResponseDTO logout() {
        logger.info("收到请求->退出登陆");
//        sysService.logout();
        logger.info("返回结果->退出登录结束");
        return ResponseDTO.returnSuccess();
    }


}

package org.example.application.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsLogin;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.SysMenu;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 发起登录请求
     *
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @JwtIgnore
    @PostMapping("/login")
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        String token = sysService.loginCheck(form);
        log.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }


    /**
     * 获取系统菜单
     *
     * @return 返回当前用户拥有的系统菜单
     */
    @GetMapping("/getSysMenus")
    @ResponseBody
    public ResponseDTO getSysMenus(HttpServletRequest request) {
        log.info("收到请求->获取用户菜单列表");
        List<SysMenu> menus = sysService.getSysMenus(request);
        log.info("获取菜单列表成功:[{}]", menus);
        return ResponseDTO.returnSuccess("操作成功", menus);
    }

}

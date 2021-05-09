package org.example.system.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.example.common.BaseController;
import org.example.common.groups.IsLogin;
import org.example.common.utils.jwt.JwtIgnore;
import org.example.system.dto.ResponseDTO;
import org.example.system.entity.SysMenu;
import org.example.system.entity.SysUser;
import org.example.system.form.UserForm;
import org.example.system.service.SysService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
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
    @PostMapping("/loginCheck")
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        String token = sysService.loginCheck(form);
        log.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }

    @JwtIgnore
    @GetMapping("/logout")
    public ResponseDTO logout() {
        log.info("收到请求->退出登录");
//        sysService.logout();
        return ResponseDTO.returnSuccess("操作成功");
    }
}

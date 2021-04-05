package cn.crabapples.application.system.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.common.utils.jwt.JwtIgnore;
import cn.crabapples.application.system.dto.ResponseDTO;
import cn.crabapples.application.system.entity.SysMenu;
import cn.crabapples.application.system.entity.SysUser;
import cn.crabapples.application.system.form.UserForm;
import cn.crabapples.application.system.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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
    @PostMapping("/login")
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        String token = sysService.loginCheck(form);
        log.info("登录验证结束->token:[{}]", token);
        return ResponseDTO.returnSuccess("登录成功", token);
    }

    /**
     * 进入主页面
     *
     * @return 登录后的主页面
     */
    @GetMapping("/index")
//    @RequiresPermissions("abc:wewe")
    public String index() {
        log.info("收到请求->进入主页");
        return "index";
    }

    /**
     * 获取系统菜单
     *
     * @return 返回当前用户拥有的系统菜单
     */
    @GetMapping("/getSysMenus")
    @ResponseBody
    public ResponseDTO getSysMenus() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        log.info("收到请求->获取用户[{}]的菜单列表", user.getId());
        List<SysMenu> menus = sysService.getSysMenus(user);
        log.info("获取菜单列表成功:[{}]", menus);
        return ResponseDTO.returnSuccess("操作成功", menus);
    }

}

package org.example.fangwuzulin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.fangwuzulin.config.groups.IsAdd;
import org.example.fangwuzulin.config.groups.IsEdit;
import org.example.fangwuzulin.config.groups.IsLogin;
import org.example.fangwuzulin.dto.ResponseDTO;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;
import org.example.fangwuzulin.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController extends BaseController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private static final String PAGE = "portal/";

    @GetMapping("/page/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", PAGE + pageName);
        return PAGE + pageName;
    }

    @GetMapping("/page/{dir}/{pageName}")
    public String dirPage(@PathVariable("dir") String dir, @PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入二级页面[{}]", PAGE + dir + "/" + pageName);
        return PAGE + dir + "/" + pageName;
    }

    @RequestMapping("/")
    public String index() {
        return toIndex();
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/registry")
    public String registry() {
        return "registry";
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        SysUser sysUser = indexService.loginOnShiro(form);
        logger.info("登录验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @PostMapping("/registryCheck")
    @ResponseBody
    public ResponseDTO registryCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户注册[{}]", form);
        super.validator(form, IsAdd.class);
        SysUser sysUser = indexService.registry(form);
        logger.info("注册验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("注册成功，即将返回登录页面", sysUser);
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    @RequiresPermissions("login")
    public ResponseDTO getUserInfo() {
        logger.info("收到请求->获取用户信息");
        SysUser sysUser = indexService.getUserInfo();
        logger.info("返回结果->获取用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @PostMapping("/saveUserInfo")
    @ResponseBody
    @RequiresPermissions("login")
    public ResponseDTO saveUserInfo(@RequestBody UserForm form) {
        logger.info("收到请求->修改用户信息");
        super.validator(form, IsEdit.class);
        indexService.updateUserInfo(form);
        logger.info("返回结果->修改用户信息结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/loginOut")
    public String loginOut() {
        logger.info("收到请求->退出登录");
        indexService.logout();
        logger.info("返回结果->退出登录结束");
        return "index";
    }
}

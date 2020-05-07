package org.example.gongjiao.controller;

import org.example.gongjiao.config.groups.IsLogin;
import org.example.gongjiao.dto.ResponseDTO;
import org.example.gongjiao.entity.SysUser;
import org.example.gongjiao.form.UserForm;
import org.example.gongjiao.service.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/manage")
public class SysController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysController.class);
    private SysService sysService;
    private static final String MANAGE = "manage/";

    public SysController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping("/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", MANAGE + pageName);
        return MANAGE + pageName;
    }

    @PostMapping("/registryCheck")
    @ResponseBody
    public ResponseDTO registryCheck(@RequestBody Map<String, String> map) {
//        super.validator(form, IsLogin.class);
        logger.info("收到请求->用户注册[{}]", map);
        SysUser sysUser = sysService.registry(map);
        logger.info("注册验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("注册成功，即将返回登录页面", sysUser);
    }

    /**
     * 发起登录请求
     *
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        ResponseDTO responseDTO = sysService.login(form);
        logger.info("登录验证结束->用户信息:[{}]", responseDTO);
        return responseDTO;
    }


    @GetMapping("/getUserList")
    @ResponseBody
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> sysUsers = sysService.getUserList();
        logger.info("返回结果->获取用户列表:[{}]", sysUsers);
        return ResponseDTO.returnSuccess("操作成功", sysUsers);
    }

    @PutMapping("/changeStatus/{id}")
    @ResponseBody
    public ResponseDTO changeStatus(@PathVariable String id) {
        logger.info("收到请求->修改用户状态");
        sysService.changeStatus(id);
        logger.info("返回结果->修改用户状态结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @DeleteMapping("/removeUserById/{id}")
    @ResponseBody
    public ResponseDTO removeUserById(@PathVariable String id) {
        logger.info("收到请求->修改用户状态");
        sysService.removeUserById(id);
        logger.info("返回结果->修改用户状态结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/saveUserInfo")
    @ResponseBody
    public ResponseDTO saveUserInfo(@RequestBody SysUser sysUser) {
        logger.info("收到请求->修改用户信息");
        sysService.saveUserInfo(sysUser);
        logger.info("返回结果->修改用户信息结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/savePassword")
    @ResponseBody
    public ResponseDTO savePassword(@RequestBody Map map) {
        logger.info("收到请求->修改用户密码");
        sysService.savePassword(map);
        logger.info("返回结果->修改用户密码结束");
        return ResponseDTO.returnSuccess("操作成功");
    }
}

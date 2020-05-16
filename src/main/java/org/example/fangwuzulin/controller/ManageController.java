package org.example.fangwuzulin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.fangwuzulin.dto.ResponseDTO;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
@RequiresPermissions("manage")
public class ManageController extends BaseController {

    private final ManageService manageService;
    private final Logger logger = LoggerFactory.getLogger(ManageController.class);
    private static final String MANAGE = "manage/";

    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }


    @RequestMapping("/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", MANAGE + pageName);
        return MANAGE + pageName;
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> sysUsers = manageService.getUserList();
        logger.info("返回结果->获取用户列表:[{}]", sysUsers);
        return ResponseDTO.returnSuccess("操作成功", sysUsers);
    }

    @RequestMapping("/changeStatus/{id}")
    @ResponseBody
    public ResponseDTO changeStatus(@PathVariable String id) {
        logger.info("收到请求->修改用户状态");
        manageService.changeStatus(id);
        logger.info("返回结果->修改用户状态结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/removeUserById/{id}")
    @ResponseBody
    public ResponseDTO removeUserById(@PathVariable String id) {
        logger.info("收到请求->修改用户状态");
        manageService.removeUserById(id);
        logger.info("返回结果->修改用户状态结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/savePassword")
    @ResponseBody
    public ResponseDTO savePassword(@RequestBody Map map) {
        logger.info("收到请求->修改用户密码");
        manageService.changePassword(map);
        logger.info("返回结果->修改用户密码结束");
        return ResponseDTO.returnSuccess("操作成功");
    }
}

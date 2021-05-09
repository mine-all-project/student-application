package org.example.system.controller;

import org.example.common.BaseController;
import org.example.common.groups.IsAdd;
import org.example.common.groups.IsEdit;
import org.example.common.groups.IsStatusChange;
import org.example.system.dto.ResponseDTO;
import org.example.system.entity.SysUser;
import org.example.system.form.UserForm;
import org.example.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * TODO 用户管理接口类
 *
 * @author Mr.He
 * 2020/1/27 2:09
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseDTO addUser(@RequestBody UserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
        super.validator(form, IsAdd.class);
        SysUser user = userService.addUser(form);
        logger.info("返回结果->用户添加完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/editUser")
    public ResponseDTO editUser(@RequestBody UserForm form) {
        logger.info("收到请求->修改用户:[{}]", form);
        super.validator(form, IsEdit.class);
        SysUser user = userService.editUser(form);
        logger.info("返回结果->用户修改完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/delUser")
    public ResponseDTO delUser(@RequestBody UserForm form) {
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->删除用户:[{}]", form.getId());
        userService.delUser(form.getId());
        logger.info("返回结果->用户删除完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/changeStatus")
    public ResponseDTO changeStatus(@RequestBody UserForm form) {
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->修改用户状态,id:[{}]", form.getId());
        userService.changeStatus(form.getId());
        logger.info("返回结果->修改用户状态完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/getUserInfo")
    public ResponseDTO getUserInfo() {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = userService.getUserInfo();
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

}

package org.example.application.custom.controller;

import org.example.application.common.BaseController;
import org.example.application.common.groups.IsEdit;
import org.example.application.common.groups.IsNotNull;
import org.example.application.common.groups.IsStatusChange;
import org.example.application.custom.dto.ResponseDTO;
import org.example.application.custom.dto.SysUserDTO;
import org.example.application.custom.form.ResetPasswordForm;
import org.example.application.custom.form.TagListForm;
import org.example.application.custom.form.UserForm;
import org.example.application.custom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户管理接口类
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final org.example.application.custom.service.UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public org.example.application.custom.dto.ResponseDTO addUser(@RequestBody org.example.application.custom.form.UserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
        org.example.application.custom.SysUser user = userService.addUser(form);
        logger.info("返回结果->用户添加完成:[{}]", user);
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/editUser")
    public org.example.application.custom.dto.ResponseDTO editUser(@RequestBody org.example.application.custom.form.UserForm form) {
        logger.info("收到请求->修改用户:[{}]", form);
        super.validator(form, IsEdit.class);
        org.example.application.custom.SysUser user = userService.editUser(form);
        logger.info("返回结果->用户修改完成:[{}]", user);
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/delUser")
    public org.example.application.custom.dto.ResponseDTO delUser(@RequestBody org.example.application.custom.form.UserForm form) {
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->删除用户:[{}]", form.getId());
        userService.delUser(form.getId());
        logger.info("返回结果->用户删除完成");
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/changeStatus")
    public org.example.application.custom.dto.ResponseDTO changeStatus(@RequestBody UserForm form) {
        super.validator(form, IsStatusChange.class);
        logger.info("收到请求->修改用户状态,id:[{}]", form.getId());
        userService.changeStatus(form.getId());
        logger.info("返回结果->修改用户状态完成");
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/resetPassword")
    public org.example.application.custom.dto.ResponseDTO resetPassword(@RequestBody org.example.application.custom.form.ResetPasswordForm form) {
        super.validator(form, IsNotNull.class);
        logger.info("收到请求->重置密码,id:[{}]", form.getId());
        userService.resetPassword(form);
        logger.info("返回结果->重置密码完成");
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/updatePassword")
    public org.example.application.custom.dto.ResponseDTO updatePassword(@RequestBody ResetPasswordForm form) {
        super.validator(form, IsNotNull.class);
        logger.info("收到请求->修改密码,id:[{}]", form.getId());
        userService.updatePassword(form);
        logger.info("返回结果->修改密码完成");
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/getUserInfo")
    public org.example.application.custom.dto.ResponseDTO getUserInfo(HttpServletRequest request) {
        logger.info("收到请求->获取当前用户信息");
        org.example.application.custom.SysUser sysUser = userService.getUserInfo(request);
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @GetMapping("/getUserListDTO")
    public org.example.application.custom.dto.ResponseDTO getUserListDTO(HttpServletRequest request) {
        logger.info("收到请求->获取用户列表DTO");
        List<SysUserDTO> list = userService.getUserListDTO(request);
        logger.info("返回结果->获取用户列表DTO结束:[{}]", list);
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功", list);
    }

    @GetMapping("/list")
    public org.example.application.custom.dto.ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> list = userService.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return org.example.application.custom.dto.ResponseDTO.returnSuccess("操作成功", list);
    }

    @PostMapping("/updateTags")
    public org.example.application.custom.dto.ResponseDTO updateTags(HttpServletRequest request, @RequestBody TagListForm form) {
        logger.info("收到请求->修改用户标签");
        userService.updateTags(request, form);
        logger.info("返回结果->修改用户标签");
        return ResponseDTO.returnSuccess("操作成功");
    }
}

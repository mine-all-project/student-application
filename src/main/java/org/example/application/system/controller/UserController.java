package org.example.application.system.controller;

import org.example.application.common.BaseController;
import org.example.application.common.groups.IsEdit;
import org.example.application.common.groups.IsNotNull;
import org.example.application.common.groups.IsStatusChange;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.UserService;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/updateHeadImage")
    public ResponseDTO updateHeadImage(HttpServletRequest request, @RequestBody FileInfo headImage) {
        logger.info("收到请求->修改头像:[{}]", headImage);
        SysUser user = userService.updateHeadImage(request, headImage);
        logger.info("返回结果->修改头像完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/updateName")
    public ResponseDTO updateName(HttpServletRequest request, @RequestParam("name") String name) {
        logger.info("收到请求->修改名称:[{}]", name);
        SysUser user = userService.updateName(request, name);
        logger.info("返回结果->修改名称完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/updatePassword")
    public ResponseDTO updatePassword(HttpServletRequest request,@RequestBody ResetPasswordForm form) {
        super.validator(form, IsNotNull.class);
        logger.info("收到请求->修改密码,id:[{}]", form.getId());
        userService.updatePassword(request,form);
        logger.info("返回结果->修改密码完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/addUser")
    public ResponseDTO addUser(@RequestBody UserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
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

    @PostMapping("/resetPassword")
    public ResponseDTO resetPassword(@RequestBody ResetPasswordForm form) {
        super.validator(form, IsNotNull.class);
        logger.info("收到请求->重置密码,id:[{}]", form.getId());
        userService.resetPassword(form);
        logger.info("返回结果->重置密码完成");
        return ResponseDTO.returnSuccess("操作成功");
    }



    @PostMapping("/updateUserInfo")
    public ResponseDTO updateUserInfo(@RequestBody UpdateUserInfoForm form) {
        super.validator(form, IsNotNull.class);
        logger.info("收到请求->修改用户信息,id:[{}]", form.getId());
        userService.updateUserInfo(form);
        logger.info("返回结果->修改用户信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/getUserInfo")
    public ResponseDTO getUserInfo(HttpServletRequest request) {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = userService.getUserInfo(request);
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }


    @GetMapping("/list")
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> list = userService.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

//1bbd886460827015e5d605ed44252251
}

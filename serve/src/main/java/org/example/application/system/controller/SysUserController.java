package org.example.application.system.controller;

import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SysUserService;
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
public class SysUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/addUser")
    public ResponseDTO addUser(@RequestBody UserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
        SysUser user = sysUserService.addUser(form);
        logger.info("返回结果->用户添加完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/editUser")
    public ResponseDTO editUser(@RequestBody UserForm form) {
        logger.info("收到请求->修改用户:[{}]", form);
        super.validator(form, Groups.IsEdit.class);
        SysUser user = sysUserService.editUser(form);
        logger.info("返回结果->用户修改完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/delUser")
    public ResponseDTO delUser(@RequestBody UserForm form) {
        super.validator(form, Groups.IsStatusChange.class);
        logger.info("收到请求->删除用户:[{}]", form.getId());
        sysUserService.delUser(form.getId());
        logger.info("返回结果->用户删除完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/changeStatus")
    public ResponseDTO changeStatus(@RequestBody UserForm form) {
        super.validator(form, Groups.IsStatusChange.class);
        logger.info("收到请求->修改用户状态,id:[{}]", form.getId());
        sysUserService.changeStatus(form.getId());
        logger.info("返回结果->修改用户状态完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/resetPassword")
    public ResponseDTO resetPassword(@RequestBody ResetPasswordForm form) {
        super.validator(form, Groups.IsNotNull.class);
        logger.info("收到请求->重置密码,id:[{}]", form.getId());
        sysUserService.resetPassword(form);
        logger.info("返回结果->重置密码完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/updatePassword")
    public ResponseDTO updatePassword(@RequestBody ResetPasswordForm form) {
        super.validator(form, Groups.IsNotNull.class);
        logger.info("收到请求->修改密码,id:[{}]", form.getId());
        sysUserService.updatePassword(form);
        logger.info("返回结果->修改密码完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/updateUserInfo")
    public ResponseDTO updateUserInfo(@RequestBody UpdateUserInfoForm form) {
        super.validator(form, Groups.IsNotNull.class);
        logger.info("收到请求->修改用户信息,id:[{}]", form.getId());
        sysUserService.updateUserInfo(form);
        logger.info("返回结果->修改用户信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/getUserInfo")
    public ResponseDTO getUserInfo(HttpServletRequest request) {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = sysUserService.getUserInfo(request);
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @GetMapping("/list")
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> list = sysUserService.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @PostMapping("/order/checkPass/{id}")
    public ResponseDTO orderCheckPass(@PathVariable String id) {
        logger.info("收到请求->允许管理账单[{}]", id);
        sysUserService.orderCheckPass(id);
        logger.info("返回结果->允许管理账单结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/order/checkFail/{id}")
    public ResponseDTO orderCheckFail(@PathVariable String id) {
        logger.info("收到请求->禁止管理账单,id:[{}]", id);
        sysUserService.orderCheckFail(id);
        logger.info("返回结果->禁止管理账单结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/orderCount/checkPass/{id}")
    public ResponseDTO orderCountCheckPass(@PathVariable String id) {
        logger.info("收到请求->允许管理账务,id:[{}]", id);
        sysUserService.orderCountCheckPass(id);
        logger.info("返回结果->允许管理账务结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/orderCount/checkFail/{id}")
    public ResponseDTO orderCountCheckFail(@PathVariable String id) {
        logger.info("收到请求->禁止管理账务,id:[{}]", id);
        sysUserService.orderCountCheckFail(id);
        logger.info("返回结果->禁止管理账务结束");
        return ResponseDTO.returnSuccess();
    }
}

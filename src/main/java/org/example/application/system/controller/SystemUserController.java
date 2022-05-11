package org.example.application.system.controller;

import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.SystemUser;
import org.example.application.system.form.ResetPasswordForm;
import org.example.application.system.form.UpdateUserInfoForm;
import org.example.application.system.form.UserForm;
import org.example.application.system.service.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户接口类
 */
@RestController
@RequestMapping(value = "/api/user")
public class SystemUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SystemUserController.class);
    private final SystemUserService sysUserService;

    public SystemUserController(SystemUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/addUser")
    public ResponseDTO addUser(@RequestBody UserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
        SystemUser user = sysUserService.addUser(form);
        logger.info("返回结果->用户添加完成:[{}]", user);
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    @PostMapping("/editUser")
    public ResponseDTO editUser(@RequestBody UserForm form) {
        logger.info("收到请求->修改用户:[{}]", form);
        super.validator(form, Groups.IsEdit.class);
        SystemUser user = sysUserService.editUser(form);
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


    @GetMapping("/list")
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SystemUser> list = sysUserService.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @PostMapping("/goods/checkPass/{id}")
    public ResponseDTO goodsCheckPass(@PathVariable String id) {
        logger.info("收到请求->允许管理商品[{}]", id);
        sysUserService.goodsCheckPass(id);
        logger.info("返回结果->允许管理商品结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/goods/checkFail/{id}")
    public ResponseDTO goodsCheckFail(@PathVariable String id) {
        logger.info("收到请求->禁止管理商品,id:[{}]", id);
        sysUserService.goodsCheckFail(id);
        logger.info("返回结果->禁止管理商品结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/paper/checkPass/{id}")
    public ResponseDTO paperCheckPass(@PathVariable String id) {
        logger.info("收到请求->允许管理信息,id:[{}]", id);
        sysUserService.paperCheckPass(id);
        logger.info("返回结果->允许管理信息结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/paper/checkFail/{id}")
    public ResponseDTO paperCheckFail(@PathVariable String id) {
        logger.info("收到请求->禁止管理信息,id:[{}]", id);
        sysUserService.paperCheckFail(id);
        logger.info("返回结果->禁止管理信息结束");
        return ResponseDTO.returnSuccess();
    }
}

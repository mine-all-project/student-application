package cn.crabapples.application.system.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import cn.crabapples.application.common.groups.IsStatusChange;
import cn.crabapples.application.system.dto.ResponseDTO;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import cn.crabapples.application.system.form.UserForm;
import cn.crabapples.application.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
        super.validator(form, cn.crabapples.application.common.groups.IsStatusChange.class);
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
    public ResponseDTO getUserInfo(HttpServletRequest request) {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = userService.getUserInfo(request);
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @GetMapping("/getUserListDTO")
    public ResponseDTO getUserListDTO(HttpServletRequest request) {
        logger.info("收到请求->获取用户列表DTO");
        List<SysUserDTO> list = userService.getUserListDTO(request);
        logger.info("返回结果->获取用户列表DTO结束:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @GetMapping("/list")
    public ResponseDTO getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> list = userService.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }
}

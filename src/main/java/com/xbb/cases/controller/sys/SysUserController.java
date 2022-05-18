package com.xbb.cases.controller.sys;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    private String prefix = "/sys/user/";

    @Resource
    private UserMapper userMapper;

    // 患者管理
    @RequestMapping("/user.html")
    public String userHtml() {
        return prefix + "user";
    }

    // 护士管理
    @RequestMapping("/nurse.html")
    public String nurseHtml() {
        return prefix + "nurse";
    }

    // 医生管理
    @RequestMapping("/doctor.html")
    public String doctorHtml() {
        return prefix + "doctor";
    }

    // 管理员管理
    @RequestMapping("/admin.html")
    public String adminHtml() {
        return prefix + "admin";
    }

    // 分页数据
    @ResponseBody
    @RequestMapping("/data")
    public RespResult pageData(@RequestParam("page") Integer page,
                               @RequestParam("limit") Integer limit,
                               @RequestParam(value = "name", required = false, defaultValue = "") String name,
                               @RequestParam(value = "cellphone", required = false, defaultValue = "") String cellphone,
                               @RequestParam(value = "sex", required = false) Integer sex,
                               @RequestParam(value = "role", required = false) Integer role) {
        if (name.equals("")) name = null;
        if (cellphone.equals("")) cellphone = null;
        List<User> data = userMapper.selectListByPaging((page - 1) * limit, limit, name, cellphone, sex, role);
        int count = userMapper.selectCountByPaging(name, cellphone, sex, role);
        RespResult respResult = new RespResult();
        respResult.success(data, count);
        return respResult;
    }

    // 添加
    @ResponseBody
    @RequestMapping("/add")
    public RespResult add(@RequestBody User user) {
        RespResult respResult = new RespResult();
        // 查询是否存在用户名
        User u = userMapper.selectByUsername(user.getUsername());
        if (u == null) {
            userMapper.insertSelective(user);
        } else {
            respResult.error("操作失败：用户名已存在");
        }
        return respResult;
    }

    // 编辑
    @ResponseBody
    @RequestMapping("/edit")
    public RespResult edit(@RequestBody User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return new RespResult();
    }

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody Integer id) {
        RespResult respResult = new RespResult();
        User user = userMapper.selectByPrimaryKey(id);
        if (user.getUsername().equals("admin")) {
            respResult.error("操作失败：超级管理员不可进行删除操作");
        } else {
            userMapper.deleteByPrimaryKey(id);
        }
        return respResult;
    }

    // 个人资料
    @RequestMapping("/personal.html")
    public String personalHtml() {
        return prefix + "personal";
    }

    // 重置密码
    @RequestMapping("/password.html")
    public String passwordHtml() {
        return prefix + "password";
    }

    @ResponseBody
    @RequestMapping("/personal/edit")
    public RespResult personalEdit(@RequestBody User user, HttpSession session) {
        userMapper.updateByPrimaryKeySelective(user);
        // 更新 session
        User u = userMapper.selectByPrimaryKey(user.getId());
        session.removeAttribute(SessionConstant.KEY_USER);
        session.setAttribute(SessionConstant.KEY_USER, u);
        return new RespResult();
    }

}

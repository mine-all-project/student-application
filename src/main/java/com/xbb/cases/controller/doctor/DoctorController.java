package com.xbb.cases.controller.doctor;

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
@RequestMapping("/doctor/user")
public class DoctorController {

    private String prefix = "/doctor/user/";

    @Resource
    private UserMapper userMapper;

    // 患者管理
    @RequestMapping("/user.html")
    public String userHtml () {
        return prefix + "user";
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
        List<User> data = userMapper.selectListByPaging(
                (page - 1) * limit,
                limit,
                name,
                cellphone,
                sex,
                role
        );
        int count = userMapper.selectCountByPaging(
                name,
                cellphone,
                sex,
                role
        );
        RespResult respResult = new RespResult();
        respResult.success(data, count);
        return respResult;
    }

    // 个人资料
    @RequestMapping("/personal.html")
    public String personalHtml () {
        return prefix + "personal";
    }

    // 重置密码
    @RequestMapping("/password.html")
    public String passwordHtml () {
        return prefix + "password";
    }

    @ResponseBody
    @RequestMapping("/personal/edit")
    public RespResult personalEdit (@RequestBody User user, HttpSession session) {
        userMapper.updateByPrimaryKeySelective(user);
        // 更新 session
        User u = userMapper.selectByPrimaryKey(user.getId());
        session.removeAttribute(SessionConstant.KEY_USER);
        session.setAttribute(SessionConstant.KEY_USER, u);
        return new RespResult();
    }

}

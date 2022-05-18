package com.xbb.cases.controller;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginRegisterController {

    @Resource
    private UserMapper userMapper;

    // 注册
    @RequestMapping("/register.html")
    public String registerHtml () {
        return "register";
    }

    // 登录
    @RequestMapping("/login.html")
    public String loginHtml() {
        return "login";
    }

    // 注册
    @ResponseBody
    @RequestMapping("/register")
    public RespResult register(@RequestBody User user){
        RespResult respResult = new RespResult();
        // 查询用户名是否注册
        User u = userMapper.selectByUsername(user.getUsername());
        if (u == null) {
            userMapper.insertSelective(user);
        } else {
            respResult.error("注册失败：该用户名已注册");
        }
        return respResult;
    }

    // 登录
    @ResponseBody
    @RequestMapping("/login")
    public RespResult login(@RequestBody User user, HttpSession session){
        RespResult respResult = new RespResult();
        User u = userMapper.selectByUsername(user.getUsername());
        // 查询用户名是否存在
        if (u == null) {
            respResult.error("登录失败：用户名不存在");
        } else {
            // 对比密码是否一致
            if (u.getPassword().equals(user.getPassword())) {
                session.setAttribute(SessionConstant.KEY_USER, u);
                respResult.success(u);
            } else {
                respResult.error("登录失败：密码错误");
            }
        }
        return respResult;
    }

    // 登出
    @RequestMapping("/login.out")
    public String loginOut(HttpSession session) {
        session.removeAttribute(SessionConstant.KEY_USER);
        return "login";
    }

}

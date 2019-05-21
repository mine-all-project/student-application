package com.example.hzj.controller;

import com.example.hzj.entity.User;
import com.example.hzj.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    IndexService indexService;
    @RequestMapping("/")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("login")
    public String login(HttpServletRequest request, User user){
        if (indexService.login(request, user)){
            return "index";
        }
        return "";
    }
    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request){
        request.setAttribute("welcome","欢迎使用遵义医科大学双选会系统!");
        return "welcome";
    }
}

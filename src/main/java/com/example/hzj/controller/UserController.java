package com.example.hzj.controller;

import com.example.hzj.entity.User;
import com.example.hzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("mineInfo")
    public String mineInfoList(HttpServletRequest request){
        User _thisUser = userService.getMineInfoList(request);
        request.setAttribute("_thisUser",_thisUser);
        System.err.println(_thisUser.getMineInfo());
        return "mineInfo";
    }
    @RequestMapping("orgList")
    public String orgList(HttpServletRequest request){
        List<User> orgs = userService.getOrgsList();
//        User _thisUser = userService.getMineInfoList(request);
        request.setAttribute("orgs",orgs);
        System.err.println(orgs);
        return "org-list";
    }

}

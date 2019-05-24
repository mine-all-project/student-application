package com.example.hzj.controller;

import com.example.hzj.entity.Message;
import com.example.hzj.entity.User;
import com.example.hzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        request.setAttribute("orgs",orgs);
        return "org-list";
    }

    @RequestMapping("send")
    @ResponseBody
    public String send(HttpServletRequest request,Message message){
        User _this = (User)request.getSession().getAttribute("user");
        message.setFromId(_this.getId());
        userService.saveMessage(message);
        return "1";
    }

    @RequestMapping("messageList")
    public String messageList(HttpServletRequest request){
        List<Message> messages = userService.getMessageList(request);
        request.setAttribute("messages",messages);
        return "message-list";
    }

}

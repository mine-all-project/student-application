package com.example.hzj.controller;

import com.example.hzj.entity.Message;
import com.example.hzj.entity.MineInfo;
import com.example.hzj.entity.OrgInfo;
import com.example.hzj.entity.User;
import com.example.hzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController extends BaseHandle{
    @Autowired
    UserService userService;
    @RequestMapping("mineInfo")
    public String mineInfoList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        User _thisUser = userService.getMineInfoList(request);
        request.setAttribute("_thisUser",_thisUser);
        if(checkUser(user)){
            return "mineInfo";
        }
        System.err.println(_thisUser);
        return "orgInfo";
    }

    @RequestMapping("saveMineInfo")
    @ResponseBody
    public String saveMineInfo(String mineInfoId,User user,MineInfo mineInfo){
        userService.saveUser(mineInfoId,user,mineInfo);
        return "更新成功";
    }

    @RequestMapping("saveOrgInfo")
    @ResponseBody
    public String saveMineInfo(String orgInfoId, User user, OrgInfo orgInfo){
        userService.saveUser(orgInfoId,user,orgInfo);
        return "更新成功";
    }

    @RequestMapping("orgList")
    public String orgList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(checkUser(user)){
            List<User> orgs = userService.getUserList(2);
            request.setAttribute("orgs",orgs);
            return "org-list";
        }
        List<User> info = userService.getUserList(1);
        request.setAttribute("info",info);
        return "student-list";
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

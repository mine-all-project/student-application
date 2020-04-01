package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.entity.Message;
import cn.crabapples.tuole.entity.MineInfo;
import cn.crabapples.tuole.entity.OrgInfo;
import cn.crabapples.tuole.entity.User;
import cn.crabapples.tuole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        if(checkSession(request)){
            return "mineInfo";
        }
        return "orgInfo";
    }

    @RequestMapping("update")
    public String update(HttpServletRequest request){
        User _thisUser = userService.getMineInfoList(request);
        request.setAttribute("_thisUser",_thisUser);
        return "update";
    }

    @RequestMapping("yourInfo/{id}")
    public String yourInfo(HttpServletRequest request, @PathVariable("id") String id){
        User user = userService.getUserById(id);
        request.setAttribute("_thisUser",user);
        if(checkSession(request)){
            return "yourInfo-org";
        }
        return "yourInfo-stu";
    }

    @RequestMapping("saveMineInfo")
    @ResponseBody
    public String saveMineInfo(String mineInfoId, User user, MineInfo mineInfo){
        userService.saveUser(mineInfoId,user,mineInfo);
        return "更新成功";
    }
    @RequestMapping("saveUpdate")
    @ResponseBody
    public String saveUpdate(String mineInfoId,User user){
        userService.update(mineInfoId,user);
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
        if(checkSession(request)){
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
    public String send(HttpServletRequest request, Message message){
        User _this = (User)request.getSession().getAttribute("user");
        message.setFromId(_this.getId());
        message.setName(_this.getName());
        userService.saveMessage(message);
        return "1";
    }

    @RequestMapping("messageList")
    public String messageList(HttpSession session, HttpServletRequest request){
        List<Message> messages = userService.getMessageList(request);
        request.setAttribute("messages",messages);
        User user = (User) session.getAttribute("user");
        if(null != user){
            if(user.getType()==1){
                return "message-list-org";
            }
            if(user.getType()==2){
                return "message-list-stu";
            }
        }
        return "welcome";
    }

}

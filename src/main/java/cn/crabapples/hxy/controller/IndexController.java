package cn.crabapples.hxy.controller;

import cn.crabapples.hxy.entity.User;
import cn.crabapples.hxy.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseHandle {
    @Autowired
    IndexService indexService;
    @RequestMapping("/")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("login")
    public String login(HttpServletRequest request, User user){
        if (indexService.login(request, user)){
            User _this = (User) request.getSession().getAttribute("user");
            if(checkUser(_this)){
                return "studentIndex";
            }else{
                return "orgIndex";
            }
        }
        return "";
    }
    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request){
        request.setAttribute("welcome","登录成功!");
        return "welcome";
    }
}

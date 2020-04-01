package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.entity.User;
import cn.crabapples.tuole.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseHandle {
    final
    IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

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
        request.setAttribute("welcome","欢迎使用遵义医科大学双选会系统!");
        return "welcome";
    }
}

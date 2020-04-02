package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.entity.User;
import cn.crabapples.tuole.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("manage/")
public class ManageController extends BaseHandle {
    final
    IndexService indexService;

    public ManageController(IndexService indexService) {
        this.indexService = indexService;
    }

    private Logger logger = LoggerFactory.getLogger(ManageController.class);
    private final String MANAGE = "manage/";


    //    @RequestMapping("login")
//    public String login(HttpServletRequest request, User user) {
//        if (indexService.login(request, user)) {
//            User _this = (User) request.getSession().getAttribute("user");
//            if (checkSession(request)) {
//                return "studentIndex";
//            } else {
//                return "orgIndex";
//            }
//        }
//        return "";
//    }
    @RequestMapping("login")
    public String login(HttpServletRequest request, User user) {
        logger.info("收到请求->进入登录页面");
        return MANAGE + "login";
    }

    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request) {
        request.setAttribute("welcome", "欢迎使用遵义医科大学双选会系统!");
        return "welcome";
    }
}

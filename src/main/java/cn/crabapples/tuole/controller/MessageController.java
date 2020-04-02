package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.entity.User;
import cn.crabapples.tuole.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseHandle {
    final
    IndexService indexService;

    public MessageController(IndexService indexService) {
        this.indexService = indexService;
    }

    private Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final String PAGE = "page/";

    @RequestMapping("/addMessage/{template}")
    public String addMessage(@PathVariable("template") String template) {
        return "index";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, User user) {
        if (indexService.login(request, user)) {
            User _this = (User) request.getSession().getAttribute("user");
            if (checkSession(request)) {
                return "studentIndex";
            } else {
                return "orgIndex";
            }
        }
        return "";
    }

}

package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController  {
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

//    @RequestMapping("login")
//    public String login(HttpServletRequest request, SysUser sysUser) {
//        if (indexService.login(request, sysUser)) {
//            SysUser _this = (SysUser) request.getSession().getAttribute("user");
//            if (checkSession(request)) {
//                return "studentIndex";
//            } else {
//                return "orgIndex";
//            }
//        }
//        return "";
//    }

}

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
public class IndexController extends BaseHandle {
    final
    IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final String PAGE = "page/";

    @GetMapping("/page/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", pageName);
        return PAGE + pageName;
    }

    @GetMapping("/page/{dir}/{pageName}")
    public String dirPage(@PathVariable("dir") String dir, @PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入二级页面[{}/{}]", dir, pageName);
        return PAGE + dir + "/" + pageName;
    }

    @RequestMapping("/")
    public String toLogin() {
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request) {
        request.setAttribute("welcome", "欢迎使用遵义医科大学双选会系统!");
        return "welcome";
    }
}

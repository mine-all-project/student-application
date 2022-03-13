package org.example.textreader.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(PageController.class);
    private static final String SERVER = "server/";
    private static final String FRONTEND = "frontend/";

    @GetMapping("/frontend/{pageName}")
    public String frontendPage(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", FRONTEND + pageName);
        return FRONTEND + pageName;
    }
    @GetMapping("/server/{pageName}")
    public String serverPage(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", SERVER + pageName);
        return SERVER + pageName;
    }


    @GetMapping("/frontend/{dir}/{pageName}")
    public String dirPage(@PathVariable("dir") String dir, @PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入二级页面[{}]", FRONTEND + dir + "/" + pageName);
        return FRONTEND + dir + "/" + pageName;
    }

//    /**
//     * 进入首页
//     */
//    @RequestMapping("/")
//    public String index() {
//        return toIndex();
//    }
//    @RequestMapping("/index")
//    public String toIndex() {
//        return "/index";
//    }
//
//    /**
//     * 进入登录页面
//     */
//    @RequestMapping("/login")
//    public String toLogin() {
//        return "login";
//    }
//
//    /**
//     * 进入注册页面
//     */
//    @RequestMapping("/registry")
//    public String registry() {
//        return "registry";
//    }

}

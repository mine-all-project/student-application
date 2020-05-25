package org.example.tuole.controller;

import com.google.code.kaptcha.Producer;
import org.example.tuole.dto.RandomCode;
import org.example.tuole.dto.ResponseDTO;
import org.example.tuole.entity.SysUser;
import org.example.tuole.service.IndexService;
import org.example.tuole.utils.RandomCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 注解
@Controller
public class IndexController extends BaseController {
    private final IndexService indexService;
    private final Producer producer;

    public IndexController(IndexService indexService, Producer producer) {
        this.indexService = indexService;
        this.producer = producer;
    }

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private static final String PAGE = "page/";

    @GetMapping("/page/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", PAGE + pageName);
        return PAGE + pageName;
    }

    @GetMapping("/page/{dir}/{pageName}")
    public String dirPage(@PathVariable("dir") String dir, @PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入二级页面[{}]", PAGE + dir + "/" + pageName);
        return PAGE + dir + "/" + pageName;
    }

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "/index";
    }

    /**
     * 进入登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 进入注册页面
     *
     * @return
     */
    @RequestMapping("/registry")
    public String registry() {
        return "registry";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logOut")
    public String logOut() {
        indexService.logOut();
        return "/index";
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseDTO getUserInfo() {
        SysUser user = indexService.getUserInfo();
        return ResponseDTO.returnSuccess("操作成功", user);
    }

    /**
     * 生成图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/randomCode")
    public void randomCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("收到请求->生成图片验证码");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        RandomCode randomCode = new RandomCodeUtils().createRandomCode(producer);
        request.getSession().setAttribute("randomCode", randomCode.getCode());
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(randomCode.getImage(), "jpg", out);
        logger.info("返回结果->图片验证码生成完毕，code:[{}]", randomCode.getCode());
    }

}

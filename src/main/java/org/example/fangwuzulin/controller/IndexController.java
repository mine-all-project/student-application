package org.example.fangwuzulin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.example.fangwuzulin.config.groups.IsAdd;
import org.example.fangwuzulin.config.groups.IsEdit;
import org.example.fangwuzulin.config.groups.IsLogin;
import org.example.fangwuzulin.dto.RandomCode;
import org.example.fangwuzulin.dto.ResponseDTO;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.form.UserForm;
import org.example.fangwuzulin.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class IndexController extends BaseController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String index() {
        return toIndex();
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/registry")
    public String registry() {
        return "registry";
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        SysUser sysUser = indexService.loginOnShiro(form);
        logger.info("登录验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @PostMapping("/registryCheck")
    @ResponseBody
    public ResponseDTO registryCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户注册[{}]", form);
        super.validator(form, IsAdd.class);
        SysUser sysUser = indexService.registry(form);
        logger.info("注册验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("注册成功，即将返回登录页面", sysUser);
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseDTO getUserInfo() {
        logger.info("收到请求->获取用户信息");
        SysUser sysUser = indexService.getUserInfo();
        logger.info("返回结果->获取用户信息结束:[{}]", sysUser);
        return ResponseDTO.returnSuccess("操作成功", sysUser);
    }

    @PostMapping("/saveUserInfo")
    @ResponseBody
    public ResponseDTO saveUserInfo(@RequestBody UserForm form) {
        logger.info("收到请求->修改用户信息");
        super.validator(form, IsEdit.class);
        indexService.updateUserInfo(form);
        logger.info("返回结果->修改用户信息结束");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/loginOut")
    public String loginOut() {
        logger.info("收到请求->退出登录");
        indexService.logout();
        logger.info("返回结果->退出登录结束");
        return "index";
    }

    @GetMapping("/randomCode")
    public void captcha(HttpServletResponse response) throws IOException {
        logger.info("收到请求->生成图片验证码");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        RandomCode randomCode = createRandomCode();
        SecurityUtils.getSubject().getSession().setAttribute("randomCode", randomCode.getCode());
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(randomCode.getImage(), "jpg", out);
        logger.info("返回结果->图片验证码生成完毕，code:[{}]", randomCode.getCode());
    }
}

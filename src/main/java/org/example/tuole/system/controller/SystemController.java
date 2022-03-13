package org.example.tuole.system.controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.example.tuole.common.BaseController;
import org.example.tuole.common.groups.IsLogin;
import org.example.tuole.system.dto.RandomCode;
import org.example.tuole.system.dto.ResponseDTO;
import org.example.tuole.system.entity.SysUser;
import org.example.tuole.system.form.UserForm;
import org.example.tuole.system.service.SysService;
import org.example.tuole.system.utils.RandomCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Controller
@RequestMapping("/manage")
@Slf4j
public class SystemController extends BaseController {

    private final SysService sysService;
    private final Producer producer;


    public SystemController(SysService sysService,Producer producer) {
        this.sysService = sysService;
        this.producer = producer;
    }

    /**
     * 用户注册
     */
    @PostMapping("/registryCheck")
    @ResponseBody
    public ResponseDTO registryCheck(HttpServletRequest request, @RequestBody Map<String, String> map) {
        log.info("收到请求->用户注册[{}]", map);
        SysUser sysUser = sysService.registry(request,map);
        log.info("注册验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("注册成功，即将返回登录页面", sysUser);
    }

    /**
     * 用户登录
     *
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        ResponseDTO responseDTO = sysService.login(form);
        log.info("登录验证结束->用户信息:[{}]", responseDTO);
        return responseDTO;
    }

    /**
     * 生成图片验证码
     */
    @GetMapping("/randomCode")
    public void randomCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("收到请求->生成图片验证码");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        RandomCode randomCode = new RandomCodeUtils().createRandomCode(producer);
        request.getSession().setAttribute("randomCode", randomCode.getCode());
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(randomCode.getImage(), "jpg", out);
        log.info("返回结果->图片验证码生成完毕，code:[{}]", randomCode.getCode());
    }

}

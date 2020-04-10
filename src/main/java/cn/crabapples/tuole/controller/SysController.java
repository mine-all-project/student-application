package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.config.groups.IsLogin;
import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;
import cn.crabapples.tuole.service.SysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@Controller
@RequestMapping("/manage")
public class SysController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysController.class);
    private SysService sysService;
    private static final String MANAGE = "manage/";

    public SysController(SysService sysService) {
        this.sysService = sysService;
    }

    @GetMapping("/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        logger.info("收到请求->进入页面[{}]", MANAGE + pageName);
        return MANAGE + pageName;
    }

    @GetMapping("/getCodeByMail/{mail}")
    @ResponseBody
    public ResponseDTO getCodeByMail(@PathVariable("mail") String mail) {
        logger.info("收到请求->进入发送验证码[{}]", mail);
        sysService.sendCodeByMail(mail);
        return ResponseDTO.returnSuccess("验证码下发成功");
    }

    @GetMapping("/getCodeByPhone/{phone}")
    @ResponseBody
    public ResponseDTO getCodeByPhone(@PathVariable("phone") String phone) {
        logger.info("收到请求->发送验证码[{}]", phone);
        sysService.sendCodeByPhone(phone);
        return ResponseDTO.returnSuccess("验证码下发成功");
    }

    @PostMapping("/registryCheck")
    @ResponseBody
    public ResponseDTO registryCheck(@RequestBody Map<String, String> map) {
//        super.validator(form, IsLogin.class);
        logger.info("收到请求->用户注册[{}]", map);
        SysUser sysUser = sysService.registry(map);
        logger.info("注册验证结束->用户信息:[{}]", sysUser);
        return ResponseDTO.returnSuccess("注册成功，即将返回登录页面", sysUser);
    }

    /**
     * 发起登录请求
     *
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @PostMapping("/loginCheck")
    @ResponseBody
    public ResponseDTO loginCheck(@RequestBody UserForm form) {
        logger.info("收到请求->用户登陆验证:[{}]", form);
        super.validator(form, IsLogin.class);
        ResponseDTO responseDTO = sysService.login(form);
        logger.info("登录验证结束->用户信息:[{}]", responseDTO);
        return responseDTO;
    }

}

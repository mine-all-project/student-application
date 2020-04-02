package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.config.groups.IsLogin;
import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.SysMenu;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.form.UserForm;
import cn.crabapples.tuole.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private final String MANAGE = "manage/";

    public SysController(SysService sysService) {
        this.sysService = sysService;
    }

    /**
     * 进入登录页面
     *
     * @return 返回登录页面html
     */
    @RequestMapping("/login")
    public String login() {
        logger.info("收到请求->进入登录页面");
        return MANAGE + "login";
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

    /**
     * 进入主页面
     *
     * @return 登录后的主页面
     */
    @GetMapping("/index")
    public String index() {
        logger.info("收到请求->进入管理后台主页");
        return MANAGE + "index";
    }

    /**
     * 获取系统菜单
     *
     * @return 返回当前用户拥有的系统菜单
     */
    @GetMapping("/getSysMenus")
    @ResponseBody
    public ResponseDTO getSysMenus() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        logger.info("收到请求->获取用户[{}]的菜单列表", user.getId());
        List<SysMenu> menus = sysService.getSysMenus(user);
        logger.info("获取菜单列表成功:[{}]", menus);
        return ResponseDTO.returnSuccess("操作成功", menus);
    }

}

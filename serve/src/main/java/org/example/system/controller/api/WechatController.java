package org.example.system.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.example.common.BaseController;
import org.example.common.utils.ip.IpHelper;
import org.example.common.utils.jwt.JwtIgnore;
import org.example.system.dto.ResponseDTO;
import org.example.system.form.UrlSettingsForm;
import org.example.system.service.WechatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/12/11 23:18
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@RestController
@RequestMapping("/api/wx")
@Slf4j
public class WechatController extends BaseController {
    private final WechatService wechatService;

    public WechatController(WechatService wechatService) {
        this.wechatService = wechatService;
    }

    @GetMapping("/getUrlByKeyWords")
    @JwtIgnore
    public ResponseDTO getUrlByKeyWords(String keyWords) {
        log.info("收到请求->获取urlSettings:[{}]", keyWords);
        ResponseDTO urlSettings = wechatService.getUrlByKeyWords(keyWords);
        log.info("返回结果->获取urlSettings完成:[{}]", urlSettings);
        return urlSettings;
    }

    @RequestMapping("/saveUrlSettings")
    public ResponseDTO saveUrlSettings(@RequestBody UrlSettingsForm form) {
        log.info("收到请求->保存urlSettings:[{}]", form);
        ResponseDTO result = wechatService.saveUrlSettings(form);
        log.info("返回结果->保存urlSettings完成");
        return result;
    }

    @GetMapping("/getWxConfig")
    @JwtIgnore
    public ResponseDTO getWxConfig(String url) {
        log.info("收到请求->获取wxConfig,URL:[{}]", url);
        ResponseDTO result = wechatService.getWxConfig(url);
        log.info("返回结果->获取wxConfig完成:[{}]", result);
        return result;
    }

    @GetMapping("/getLocation")
    @JwtIgnore
    public ResponseDTO getLocation(HttpServletRequest request) {
        log.info("收到请求->获取Location");
        ResponseDTO result = wechatService.getLocation(request);
        log.info("返回结果->获取Location完成:[{}]", result);
        return result;
    }

    @GetMapping("/getLocationV2")
    @JwtIgnore
    public ResponseDTO getLocationV2(HttpServletRequest request) {
        log.info("收到请求->获取LocationV2");
        ResponseDTO result = wechatService.getLocationV2(request);
        log.info("返回结果->获取Location完成:[{}]", result);
        return result;
    }
}

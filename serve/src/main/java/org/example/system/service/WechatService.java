package org.example.system.service;


import org.example.system.dto.ResponseDTO;
import org.example.system.form.UrlSettingsForm;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 用户相关服务
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface WechatService {
    ResponseDTO getAccessToken();

    ResponseDTO getJsApiTicket();

    ResponseDTO getWxConfig(String url);

    ResponseDTO getUrlByKeyWords(String keywords);

    ResponseDTO saveUrlSettings(UrlSettingsForm form);

    ResponseDTO getLocation(HttpServletRequest request);

    ResponseDTO getLocationV2(HttpServletRequest request);
}

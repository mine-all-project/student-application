package org.example.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.common.utils.ip.IpHelper;
import org.example.common.utils.ipv2.IPEntity;
import org.example.common.utils.ipv2.IPUtils;
import org.example.system.dao.jpa.UrlSettingsRepository;
import org.example.system.dto.ResponseDTO;
import org.example.system.entity.UrlSettings;
import org.example.system.form.UrlSettingsForm;
import org.example.system.service.WechatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * TODO 用户相关服务实现类
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
@Slf4j
public class WechatServiceImpl implements WechatService {

    @Value("${isDebug}")
    private boolean isDebug;
    @Value("${tokenUrl}")
    private String tokenUrl;
    @Value("${appid}")
    private String appid;
    private final RedisTemplate<String, String> redisTemplate;
    private final RestTemplate restTemplate;
    private final UrlSettingsRepository urlSettingsRepository;

    public WechatServiceImpl(RedisTemplate<String, String> redisTemplate,
                             RestTemplate restTemplate, UrlSettingsRepository urlSettingsRepository) {
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
        this.urlSettingsRepository = urlSettingsRepository;
    }

    @Override
    public ResponseDTO getAccessToken() {
        String access_token = redisTemplate.opsForValue().get("wxAccessToken");
        if (ObjectUtils.isEmpty(access_token)) {
            log.info("accessToken已失效:[{}]", access_token);
            JSONObject result = restTemplate.getForObject(tokenUrl, JSONObject.class);
            log.info("重新请求accessToken数据:[{}]", result);
            access_token = Objects.requireNonNull(result).getString("access_token");
            log.info("新的accessToken为:[{}]", access_token);
            redisTemplate.opsForValue().set("wxAccessToken", access_token, 100, TimeUnit.MINUTES);
            log.info("将accessToken存入redis");
        }
        return ResponseDTO.returnSuccess("操作成功", access_token);
    }

    @Override
    public ResponseDTO getJsApiTicket() {
        String jsApiTicket = redisTemplate.opsForValue().get("wxJsApiTicket");
        if (ObjectUtils.isEmpty(jsApiTicket)) {
            log.info("jsApiTicket已失效:[{}]", jsApiTicket);
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken().getData() + "&type=jsapi";
            JSONObject result = restTemplate.getForObject(url, JSONObject.class);
            log.info("重新请求jsApiTicket数据:[{}]", result);
            jsApiTicket = Objects.requireNonNull(result).getString("ticket");
            log.info("新的jsApiTicket为:[{}]", jsApiTicket);
            redisTemplate.opsForValue().set("wxJsApiTicket", jsApiTicket, 100, TimeUnit.MINUTES);
            log.info("将jsApiTicket存入redis");
        }
        return ResponseDTO.returnSuccess("操作成功", jsApiTicket);
    }

    @Override
    public ResponseDTO getWxConfig(String url) {
        Object ticket = getJsApiTicket().getData();
        log.info("ticket:[{}]", ticket);
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = UUID.randomUUID().toString().substring(0, 8);
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = DigestUtils.sha1Hex(str);
        HashMap<String, Object> result = new HashMap<>(4);
        result.put("appId", appid);
        result.put("timestamp", timestamp);
        result.put("nonceStr", noncestr);
        result.put("signature", signature);
        log.info("result:[{}]", result);
        log.info("url:[{}]", url);
        return ResponseDTO.returnSuccess("操作成功", result);
    }

    @Override
    public ResponseDTO getUrlByKeyWords(String keywords) {
        log.info("keywords:[{}]", keywords);
        UrlSettings urlSettings = urlSettingsRepository.findByKeyWords(keywords);
        log.info("urlSettings:[{}]", urlSettings);
        return ResponseDTO.returnSuccess("操作成功", urlSettings);
    }

    @Override
    public ResponseDTO saveUrlSettings(UrlSettingsForm form) {
        UrlSettings urlSettings = new UrlSettings();
        BeanUtils.copyProperties(form, urlSettings);
        urlSettingsRepository.saveAndFlush(urlSettings);
        return ResponseDTO.returnSuccess("操作成功");
    }

    @Override
    public ResponseDTO getLocation(HttpServletRequest request) {
        String ip = request.getHeader("realip");
        log.info("ip:[{}]", ip);
        String location = IpHelper.findRegionByIp(ip);
        log.info("location:[{}]", location);
        return ResponseDTO.returnSuccess("操作成功", location);
    }

    @Override
    public ResponseDTO getLocationV2(HttpServletRequest request) {
        String ip = request.getHeader("realip");
        log.info("ip:[{}]", ip);
        IPEntity msg = IPUtils.getIPMsg(ip);
        log.info("location:[{}]", msg);
        return ResponseDTO.returnSuccess("操作成功", msg);
    }
}

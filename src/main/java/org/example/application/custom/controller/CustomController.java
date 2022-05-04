package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.service.CustomService;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.entity.FileInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/custom")
public class CustomController {
    private final CustomService customService;

    public CustomController(CustomService customService) {
        this.customService = customService;
    }

    @JwtIgnore
    @RequestMapping("/get/temperatureError")
    public ResponseDTO getTemperatureError() {
        log.info("收到请求->获取体温异常阈值");
        BigDecimal value = customService.getTemperatureError();
        log.info("返回结果->获取体温异常阈值结束:[{}]", value);
        return ResponseDTO.returnSuccess(value);
    }

    @RequestMapping("/save/temperatureError")
    public ResponseDTO saveTemperatureError(@RequestBody Map<String, Object> data) {
        String temperatureError = String.valueOf(data.getOrDefault("content", "0"));
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(temperatureError));
        log.info("收到请求->保存体温异常阈值:[{}]", value);
        customService.saveTemperatureError(value);
        log.info("返回结果->保存体温异常阈值结束");
        return ResponseDTO.returnSuccess();
    }

    @JwtIgnore
    @RequestMapping("/get/banner")
    public ResponseDTO getBanner() {
        log.info("收到请求->获取轮播图列表");
        List<FileInfo> list = customService.getBanner();
        log.info("返回结果->获取轮播图列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @RequestMapping("/save/banner")
    public ResponseDTO saveBanner(@RequestBody List<FileInfo> banners) {
        log.info("收到请求->保存轮播图列表:[{}]", banners);
        customService.saveBanner(banners);
        log.info("返回结果->保存轮播图列表结束");
        return ResponseDTO.returnSuccess();
    }

    @JwtIgnore
    @RequestMapping("/get/notice/base")
    public ResponseDTO getBaseNotice() {
        log.info("收到请求->获取全局通知");
        String data = customService.getBaseNotice();
        log.info("返回结果->获取全局通知结束:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/save/notice/base")
    public ResponseDTO saveBaseNotice(@RequestBody Map<String, Object> data) {
        String content = String.valueOf(data.getOrDefault("content", ""));
        log.info("收到请求->保存全局通知:[{}]", content);
        customService.saveBaseNotice(content);
        log.info("返回结果->保存全局通知结束");
        return ResponseDTO.returnSuccess();
    }

    @JwtIgnore
    @RequestMapping("/get/notice/temperature")
    public ResponseDTO getTemperatureNotice() {
        log.info("收到请求->获取体温通知");
        String data = customService.getTemperatureNotice();
        log.info("返回结果->获取体温通知结束:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/save/notice/temperature")
    public ResponseDTO saveTemperatureNotice(@RequestBody Map<String, Object> data) {
        String content = String.valueOf(data.getOrDefault("content", ""));
        log.info("收到请求->保存体温通知:[{}]", content);
        customService.saveTemperatureNotice(content);
        log.info("返回结果->保存体温通知结束");
        return ResponseDTO.returnSuccess();
    }

    @JwtIgnore
    @RequestMapping("/get/count")
    public ResponseDTO getCount() {
        log.info("收到请求->获取疫情信息");
        Map<String, Object> data = customService.getCount();
        log.info("返回结果->获取疫情信息结束:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/save/count")
    public ResponseDTO saveCount(@RequestBody Map<String, Object> data) {
        log.info("收到请求->保存疫情信息通知:[{}]", data);
        customService.saveCount(data);
        log.info("返回结果->保存疫情信息结束");
        return ResponseDTO.returnSuccess();
    }
}

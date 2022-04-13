package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Temperature;
import org.example.application.custom.service.TemperatureService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/temperature")
public class TemperatureController extends BaseController {
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @JwtIgnore
    @GetMapping("/list")
    public ResponseDTO getAll() {
        log.info("收到请求->获取体温列表");
        List<Temperature> list = temperatureService.getAll();
        log.info("返回结果->获取体温列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/mine")
    public ResponseDTO getMine(HttpServletRequest request) {
        log.info("收到请求->获取我的体温列表");
        List<Temperature> list = temperatureService.getMine(request);
        log.info("返回结果->获取我的体温列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveTemperature(HttpServletRequest request, @RequestBody Temperature form) {
        validator(form, Groups.IsNotNull.class);
        log.info("收到请求->保存体温:[{}]", form);
        Temperature entity = temperatureService.saveTemperature(request, form);
        log.info("返回结果->保存体温结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
}

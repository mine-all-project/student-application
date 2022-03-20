package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.CarPooling;
import org.example.application.custom.form.CarPoolingForm;
import org.example.application.custom.service.CarPoolingService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/carPooling/")
public class CarPoolingController extends BaseController {
    private final CarPoolingService carPoolingService;

    public CarPoolingController(CarPoolingService carPoolingService) {
        this.carPoolingService = carPoolingService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取拼车列表");
        List<CarPooling> list = carPoolingService.getAll();
        log.info("返回结果->获取拼车列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索拼车列表,keywords:[{}]",keywords);
        List<CarPooling> list = carPoolingService.search(keywords);
        log.info("返回结果->搜索拼车列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody CarPoolingForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布拼车:[{}]",form);
        CarPooling entity = carPoolingService.save(request,form);
        log.info("返回结果->发布拼车结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的拼车列表");
        List<CarPooling> list = carPoolingService.getListByMine(request);
        log.info("返回结果->获取我发布的拼车列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的拼车,id:[{}]",id);
        carPoolingService.deleteById(id);
        log.info("返回结果->删除我发布的拼车结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody CarPoolingForm form) {
        log.info("收到请求->拼车通过审核[{}]",form);
        carPoolingService.checkPass(form);
        log.info("返回结果->拼车通过审核结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody CarPoolingForm form) {
        log.info("收到请求->拼车驳回审核[{}]",form);
        carPoolingService.checkFail(form);
        log.info("返回结果->拼车驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

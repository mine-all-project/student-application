package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Other;
import org.example.application.custom.form.OtherForm;
import org.example.application.custom.service.OtherService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/other")
public class OtherController extends BaseController {
    private final OtherService otherService;

    public OtherController(OtherService otherService) {
        this.otherService = otherService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取其他列表");
        List<Other> list = otherService.getAll();
        log.info("返回结果->获取其他列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索其他列表,keywords:[{}]",keywords);
        List<Other> list = otherService.search(keywords);
        log.info("返回结果->搜索其他列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody OtherForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布其他:[{}]",form);
        Other entity = otherService.save(request,form);
        log.info("返回结果->发布其他结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的其他列表");
        List<Other> list = otherService.getListByMine(request);
        log.info("返回结果->获取我发布的其他列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的其他,id:[{}]",id);
        otherService.deleteById(id);
        log.info("返回结果->删除我发布的其他结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody OtherForm form) {
        log.info("收到请求->其他驳回审核[{}]",form);
        otherService.checkFail(form);
        log.info("返回结果->其他驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

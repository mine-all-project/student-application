package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Demand;
import org.example.application.custom.form.DemandForm;
import org.example.application.custom.service.DemandService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/demand/")
public class DemandController extends BaseController {
    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取需求列表");
        List<Demand> list = demandService.getAll();
        log.info("返回结果->获取需求列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索需求列表,keywords:[{}]",keywords);
        List<Demand> list = demandService.search(keywords);
        log.info("返回结果->搜索需求列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody DemandForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布需求:[{}]",form);
        Demand entity = demandService.save(request,form);
        log.info("返回结果->发布需求结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的需求列表");
        List<Demand> list = demandService.getListByMine(request);
        log.info("返回结果->获取我发布的需求列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的需求,id:[{}]",id);
        demandService.deleteById(id);
        log.info("返回结果->删除我发布的需求结束");
        return ResponseDTO.returnSuccess();
    }
}

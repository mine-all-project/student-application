package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.LostItems;
import org.example.application.custom.form.LostItemsForm;
import org.example.application.custom.service.LostItemsService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/lostItems/")
public class LostItemsController extends BaseController {
    private final LostItemsService lostItemsService;

    public LostItemsController(LostItemsService lostItemsService) {
        this.lostItemsService = lostItemsService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取失物招领列表");
        List<LostItems> list = lostItemsService.getAll();
        log.info("返回结果->获取失物招领列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索失物招领列表,keywords:[{}]",keywords);
        List<LostItems> list = lostItemsService.search(keywords);
        log.info("返回结果->搜索失物招领列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody LostItemsForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布失物招领:[{}]",form);
        LostItems entity = lostItemsService.save(request,form);
        log.info("返回结果->发布失物招领结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的失物招领列表");
        List<LostItems> list = lostItemsService.getListByMine(request);
        log.info("返回结果->获取我发布的失物招领列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的失物招领,id:[{}]",id);
        lostItemsService.deleteById(id);
        log.info("返回结果->删除我发布的失物招领结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody LostItemsForm form) {
        log.info("收到请求->失物招领通过审核[{}]",form);
        lostItemsService.checkPass(form);
        log.info("返回结果->失物招领通过审核结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody LostItemsForm form) {
        log.info("收到请求->失物招领驳回审核[{}]",form);
        lostItemsService.checkFail(form);
        log.info("返回结果->失物招领驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

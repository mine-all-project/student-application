package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paper/")
public class PaperController extends BaseController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取文章列表");
        List<Paper> list = paperService.getAll();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/list/{type}")
    public ResponseDTO getListByType(@PathVariable String type) {
        log.info("收到请求->获取文章列表,type:[{}]",type);
        List<Paper> list = paperService.getListByType(type);
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索文章列表,keywords:[{}]",keywords);
        List<Paper> list = paperService.search(keywords);
        log.info("返回结果->搜索文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody PaperForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布文章:[{}]",form);
        Paper entity = paperService.save(request,form);
        log.info("返回结果->发布文章结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的文章列表");
        List<Paper> list = paperService.getListByMine(request);
        log.info("返回结果->获取我发布的文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的文章,id:[{}]",id);
        paperService.deleteById(id);
        log.info("返回结果->删除我发布的文章结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody PaperForm form) {
        log.info("收到请求->文章通过审核[{}]",form);
        paperService.checkPass(form);
        log.info("返回结果->文章通过审核结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody PaperForm form) {
        log.info("收到请求->文章驳回审核[{}]",form);
        paperService.checkFail(form);
        log.info("返回结果->文章驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

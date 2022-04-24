package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.DIC;
import org.example.application.common.groups.IsNotNull;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/paper/")
public class PaperController extends BaseController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    private List<Paper> filterCheckStatus(List<Paper> source, int status) {
        return source.stream().filter(e -> e.getStatus() == status).collect(Collectors.toList());
    }

    @GetMapping("/list")
    @JwtIgnore
    public ResponseDTO getList() {
        log.info("收到请求->获取文章列表");
        List<Paper> list = paperService.getAll();
        list = filterCheckStatus(list, DIC.CHECK_PASS);
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/list/{type}")
    @JwtIgnore
    public ResponseDTO getListByType(@PathVariable String type) {
        log.info("收到请求->获取文章列表,type:[{}]", type);
        List<Paper> list = paperService.getListByType(type);
        list = filterCheckStatus(list, DIC.CHECK_PASS);
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/mine/list")
    public ResponseDTO getMineList(HttpServletRequest request) {
        log.info("收到请求->获取我发布的文章列表");
        List<Paper> list = paperService.getMineAll(request);
        log.info("返回结果->获取我发布的文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/mine/list/{type}")
    public ResponseDTO getMineListByType(HttpServletRequest request, @PathVariable String type) {
        log.info("收到请求->获取我发布的文章列表,type:[{}]", type);
        List<Paper> list = paperService.getMineListByType(request, type);
        log.info("返回结果->获取我发布的文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/check/list")
    public ResponseDTO getCheckList(HttpServletRequest request) {
        log.info("收到请求->获取审核列表");
        List<Paper> list = paperService.getAll();
        list = filterCheckStatus(list, DIC.CHECK_WAIT);
        log.info("返回结果->获取审核列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/check/list/{type}")
    public ResponseDTO getCheckListByType(HttpServletRequest request, @PathVariable String type) {
        log.info("收到请求->获取审核列表,type:[{}]", type);
        List<Paper> list = paperService.getListByType(type);
        list = filterCheckStatus(list, DIC.CHECK_WAIT);
        log.info("返回结果->获取审核列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/checking/list")
    public ResponseDTO getCheckingList(HttpServletRequest request) {
        log.info("收到请求->获取审核中的动态列表");
        List<Paper> list = paperService.getMineAll(request);
        list = filterCheckStatus(list, DIC.CHECK_WAIT);
        log.info("返回结果->获取审核中的动态列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/checking/list/{type}")
    public ResponseDTO getCheckingListByType(HttpServletRequest request, @PathVariable String type) {
        log.info("收到请求->获取审核中的动态列表,type:[{}]", type);
        List<Paper> list = paperService.getMineListByType(request, type);
        list = filterCheckStatus(list, DIC.CHECK_WAIT);
        log.info("返回结果->获取审核中的动态列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search/{keywords}")
    @JwtIgnore
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索文章列表,keywords:[{}]", keywords);
        List<Paper> list = paperService.search(keywords);
        log.info("返回结果->搜索文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody PaperForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布文章:[{}]", form);
        Paper entity = paperService.save(request, form);
        log.info("返回结果->发布文章结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的文章,id:[{}]", id);
        paperService.deleteById(id);
        log.info("返回结果->删除我发布的文章结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody PaperForm form) {
        log.info("收到请求->文章通过审核[{}]", form);
        paperService.checkPass(form);
        log.info("返回结果->文章通过审核结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody PaperForm form) {
        log.info("收到请求->文章驳回审核[{}]", form);
        paperService.checkFail(form);
        log.info("返回结果->文章驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

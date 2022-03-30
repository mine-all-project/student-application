package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.Groups;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paper")
public class PaperController extends BaseController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @JwtIgnore
    @GetMapping("/list")
    public ResponseDTO getAll() {
        log.info("收到请求->获取文章列表");
        List<Paper> list = paperService.getAll();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO savePaper(@RequestBody PaperForm form) {
        validator(form, Groups.IsNotNull.class);
        log.info("收到请求->保存文章:[{}]", form);
        Paper entity = paperService.savePaper(form);
        log.info("返回结果->保存文章结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
//
//    @PostMapping("/update")
//    public ResponseDTO updatePaper(@RequestBody Paper paper) {
//        log.info("收到请求->修改文章");
//        paperService.updatePaper(paper);
//        log.info("返回结果->修改文章结束:[{}]", paper);
//        return ResponseDTO.returnSuccess();
//    }

    @GetMapping("/id/{id}")
    @JwtIgnore
    public ResponseDTO findPaperById(@PathVariable String id) {
        log.info("收到请求->获取文章,id:[{}]", id);
        Paper entity = paperService.findPaperById(id);
        log.info("返回结果->获取文章结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deletePaperById(@PathVariable String id) {
        log.info("收到请求->删除文章,id:[{}]", id);
        paperService.deletePaperById(id);
        log.info("返回结果->删除文章结束");
        return ResponseDTO.returnSuccess();
    }
}

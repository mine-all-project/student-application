package org.example.textreader.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.common.ResponseDTO;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.service.PaperService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paper")
public class PaperController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取文章列表");
        final List<Paper> list = paperService.getPaperList();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/id/{id}")
    public ResponseDTO getById(@PathVariable String id) {
        log.info("收到请求->根据[id]获取文章信息,id:[{}]",id);
        Paper entity  = paperService.getPaperById(id);
        log.info("返回结果->根据[id]获取文章信息结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/save")
    public ResponseDTO save(@RequestBody PaperForm form) {
        log.info("收到请求->保存文章信息:[{}]",form);
        Paper entity = paperService.savePaper(form);
        log.info("返回结果->保存文章信息结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/del/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->根据[id]删除文章信息,id:[{}]",id);
        paperService.deleteById(id);
        log.info("返回结果->根据[id]删除文章信息结束");
        return ResponseDTO.returnSuccess();
    }


    @GetMapping("/content/{fileId}")
    public ResponseDTO getContentByFile(@PathVariable String fileId) throws IOException {
        String content = paperService.getContentByFile(fileId);
        return ResponseDTO.returnSuccess(content);
    }
}

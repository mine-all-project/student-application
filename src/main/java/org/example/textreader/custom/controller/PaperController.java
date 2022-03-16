package org.example.textreader.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.example.textreader.common.BaseController;
import org.example.textreader.common.ResponseDTO;
import org.example.textreader.common.groups.IsNotNull;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.entity.ReadInfo;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.form.ReadForm;
import org.example.textreader.custom.service.PaperService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paper")
public class PaperController extends BaseController {
    private final PaperService paperService;
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @RequiresAuthentication
    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取文章列表");
        final List<Paper> list = paperService.getPaperList();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @RequiresAuthentication
    @GetMapping("/id/{id}")
    public ResponseDTO getById(@PathVariable String id) {
        log.info("收到请求->根据[id]获取文章信息,id:[{}]", id);
        Paper entity = paperService.getPaperById(id);
        log.info("返回结果->根据[id]获取文章信息结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
    @RequiresAuthentication
    @PostMapping("/save")
    public ResponseDTO save(@RequestBody PaperForm form) {
        log.info("收到请求->保存文章信息:[{}]", form);
        Paper entity = paperService.savePaper(form);
        log.info("返回结果->保存文章信息结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
    @RequiresAuthentication
    @DeleteMapping("/del/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->根据[id]删除文章信息,id:[{}]", id);
        paperService.deleteById(id);
        log.info("返回结果->根据[id]删除文章信息结束");
        return ResponseDTO.returnSuccess();
    }

    @RequiresAuthentication
    @GetMapping("/content/{fileId}")
    public ResponseDTO getContentByFile(@PathVariable String fileId) throws IOException {
        log.info("收到请求->根据[id]获取文本内容,id:[{}]", fileId);
        String content = paperService.getContentByFile(fileId);
        log.info("返回结果->根据[id]获取文本内容结束,内容:[{}]", content);
        return ResponseDTO.returnSuccess(content);
    }
    @RequiresAuthentication
    @PostMapping("/reader/create")
    public ResponseDTO createFile(@RequestBody ReadForm form) throws IOException {
        validator(form, IsNotNull.class);
        log.info("收到请求->合成语音:[{}]", form);
        ReadInfo entity = paperService.createFile(form);
        log.info("返回结果->合成语音结束,内容:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
    @RequiresAuthentication
    @PostMapping("/reader/id/{id}")
    public ResponseDTO getReaderFileById(@PathVariable String id) {
        log.info("收到请求->根据[id][status]获取合成的语音,id:[{}]", id);
        ReadInfo entity = paperService.getReaderFileById(id);
        log.info("返回结果->根据[id][status]获取合成语音结束");
        return ResponseDTO.returnSuccess(entity);
    }
}

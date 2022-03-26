package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        List<Map> list = paperService.getAll();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/list/{typeId}")
    @JwtIgnore
    public ResponseDTO getAllByTypeId(@PathVariable String typeId) {
        log.info("收到请求->获取文章列表,typeId:[{}]",typeId);
        List<Map> list = paperService.getAllByTypeId(typeId);
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/add")
    public ResponseDTO addPaper(@RequestBody Paper paper) {
        log.info("收到请求->添加文章");
        paperService.addPaper(paper);
        log.info("返回结果->添加文章结束:[{}]", paper);
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/update")
    public ResponseDTO updatePaper(@RequestBody Paper paper) {
        log.info("收到请求->修改文章");
        paperService.updatePaper(paper);
        log.info("返回结果->修改文章结束:[{}]", paper);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/id/{id}")
    @JwtIgnore
    public ResponseDTO findPaperById(@PathVariable String id) {
        log.info("收到请求->获取文章,id:[{}]", id);
        Map entity = paperService.findPaperById(id);
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

package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.PaperType;
import org.example.application.custom.service.PaperTypeService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paperType")
public class PaperTypeController extends BaseController {
    private final PaperTypeService paperTypeService;

    public PaperTypeController(PaperTypeService paperTypeService) {
        this.paperTypeService = paperTypeService;
    }

    @JwtIgnore
    @GetMapping("/list")
    public ResponseDTO typeList() {
        log.info("收到请求->获取文章类型列表");
        List<PaperType> list = paperTypeService.getAllTypes();
        log.info("返回结果->获取文章类型列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/add")
    public ResponseDTO addPaperType(@RequestBody PaperType type) {
        log.info("收到请求->添加文章类型");
        paperTypeService.addPaperType(type);
        log.info("返回结果->添加文章类型结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/update")
    public ResponseDTO updatePaperType(@RequestBody PaperType type) {
        log.info("收到请求->修改文章类型");
        paperTypeService.updatePaperType(type);
        log.info("返回结果->修改文章类型结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/id/{id}")
    public ResponseDTO findPaperTypeById(@PathVariable String id) {
        log.info("收到请求->获取文章类型,id:[{}]", id);
        PaperType entity = paperTypeService.findPaperTypeById(id);
        log.info("返回结果->获取文章类型结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deletePaperTypeById(@PathVariable String id) {
        log.info("收到请求->删除文章类型,id:[{}]", id);
        paperTypeService.deletePaperTypeById(id);
        log.info("返回结果->删除文章类型结束");
        return ResponseDTO.returnSuccess();
    }
}

package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Paper;
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
    @GetMapping("/list")
    public ResponseDTO getAll() {
        log.info("收到请求->获取文章列表");
        List<Paper> list = paperService.getAll();
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/list/{typeId}")
    public ResponseDTO getAllByTypeId(@PathVariable String typeId) {
        log.info("收到请求->获取文章列表,typeId:[{}]",typeId);
        List<Paper> list = paperService.getAllByTypeId(typeId);
        log.info("返回结果->获取文章列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/add")
    public ResponseDTO addPaper(@RequestBody Paper type) {
        log.info("收到请求->添加文章");
        paperService.addPaper(type);
        log.info("返回结果->添加文章结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/update")
    public ResponseDTO updatePaper(@RequestBody Paper type) {
        log.info("收到请求->修改文章");
        paperService.updatePaper(type);
        log.info("返回结果->修改文章结束:[{}]", type);
        return ResponseDTO.returnSuccess();
    }

    @GetMapping("/id/{id}")
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

//    @GetMapping("/search/{keywords}")
//    public ResponseDTO search(@PathVariable String keywords) {
//        log.info("收到请求->搜索商品列表,keywords:[{}]",keywords);
//        List<Goods> list = goodsService.search(keywords);
//        log.info("返回结果->搜索商品列表结束:[{}]", list);
//        return ResponseDTO.returnSuccess(list);
//    }
//    @PostMapping("/checkPass")
//    public ResponseDTO checkPass(@RequestBody GoodsForm form) {
//        log.info("收到请求->商品通过审核[{}]",form);
//        goodsService.checkPass(form);
//        log.info("返回结果->商品通过审核结束");
//        return ResponseDTO.returnSuccess();
//    }
//    @PostMapping("/checkFail")
//    public ResponseDTO checkFail(@RequestBody GoodsForm form) {
//        log.info("收到请求->商品驳回审核[{}]",form);
//        goodsService.checkFail(form);
//        log.info("返回结果->商品驳回审核结束");
//        return ResponseDTO.returnSuccess();
//    }
}

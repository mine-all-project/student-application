package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.News;
import org.example.application.custom.form.NewsForm;
import org.example.application.custom.service.NewsService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/news")
public class NewsController extends BaseController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取咨询列表");
        List<News> list = newsService.getAll();
        log.info("返回结果->获取咨询列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索咨询列表,keywords:[{}]", keywords);
        List<News> list = newsService.search(keywords);
        log.info("返回结果->搜索咨询列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody NewsForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布咨询:[{}]", form);
        News entity = newsService.save(request, form);
        log.info("返回结果->发布咨询结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的咨询列表");
        List<News> list = newsService.getListByMine(request);
        log.info("返回结果->获取我发布的咨询列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的咨询,id:[{}]", id);
        newsService.deleteById(id);
        log.info("返回结果->删除我发布的咨询结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody NewsForm form) {
        log.info("收到请求->咨询过审核[{}]", form);
        newsService.checkPass(form);
        log.info("返回结果->咨询通过审核结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody NewsForm form) {
        log.info("收到请求->咨询驳回审核[{}]", form);
        newsService.checkFail(form);
        log.info("返回结果->咨询驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

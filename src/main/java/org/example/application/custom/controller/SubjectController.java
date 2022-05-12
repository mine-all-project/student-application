package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.CheckForm;
import org.example.application.custom.form.StatusForm;
import org.example.application.custom.service.SubjectService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/subject")
public class SubjectController extends BaseController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/list")
    @JwtIgnore
    public ResponseDTO getList() {
        log.info("收到请求->获取课题列表");
        List<Subject> list = subjectService.getAll();
        log.info("返回结果->获取课题列表->结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/list/{status}")
    @JwtIgnore
    public ResponseDTO getList(@PathVariable String status) {
        log.info("收到请求->获取课题列表,status:[{}]", status);
        List<Subject> list = subjectService.getAll(status);
        log.info("返回结果->获取课题列表->结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search")
    public ResponseDTO search() {
        return getList();
    }

    @GetMapping("/search/{status}")
    public ResponseDTO search(@PathVariable String status) {
        log.info("收到请求->搜索课题");
        return getList(status);
    }

    @GetMapping("/search/{status}/{keyword}")
    public ResponseDTO searchByKeyword(@PathVariable String status, @PathVariable String keyword) {
        log.info("收到请求->搜索课题,keyword:[{}]", keyword);
        List<Subject> list = subjectService.searchSubject(status, keyword);
        log.info("返回结果->搜索课题->结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO saveSubject(HttpServletRequest request, @RequestBody Subject form) {
        log.info("收到请求->保存课题:[{}]", form);
        Subject entity = subjectService.saveSubject(request, form);
        log.info("返回结果->保存课题->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteSubject(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->删除课题:[{}]", id);
        subjectService.deleteSubject(request, id);
        log.info("返回结果->删除课题->结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/check")
    public ResponseDTO checkSubject(HttpServletRequest request, @RequestBody CheckForm form) {
        log.info("收到请求->审核课题:[{}]", form);
        Subject entity = subjectService.checkSubject(request, form);
        log.info("返回结果->审核课题->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/status/edit")
    public ResponseDTO editStatusContent(HttpServletRequest request, @RequestBody StatusForm form) {
        log.info("收到请求->填写任务:[{}]", form);
        Subject entity = subjectService.editStatusContent(request, form);
        log.info("返回结果->填写任务->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/select/random")
    public ResponseDTO selectSubject(HttpServletRequest request) {
        log.info("收到请求->选择课题");
        Subject entity = subjectService.selectSubject(request);
        log.info("返回结果->选择课题->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/select/id/{id}")
    public ResponseDTO selectSubject(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->选择课题:[{}]", id);
        Subject entity = subjectService.selectSubject(request, id);
        log.info("返回结果->选择课题->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/select/mine")
    public ResponseDTO mineSubject(HttpServletRequest request) {
        log.info("收到请求->我的课题");
        Subject entity = subjectService.mineSubject(request);
        log.info("返回结果->我的课题->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/first/edit")
    public ResponseDTO editFirstContent(HttpServletRequest request, @RequestBody StatusForm form) {
        log.info("收到请求->撰写一稿:[{}]", form);
        Subject entity = subjectService.editFirstContent(request, form);
        log.info("返回结果->撰写一稿->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/first/check")
    public ResponseDTO checkFirstContent(HttpServletRequest request, @RequestBody CheckForm form) {
        log.info("收到请求->中期检查:[{}]", form);
        Subject entity = subjectService.checkFirstContent(request, form);
        log.info("返回结果->中期检查->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @PostMapping("/last/edit")
    public ResponseDTO editLastContent(HttpServletRequest request, @RequestBody StatusForm form) {
        log.info("收到请求->撰写终稿:[{}]", form);
        Subject entity = subjectService.editLastContent(request, form);
        log.info("返回结果->撰写终稿->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

}

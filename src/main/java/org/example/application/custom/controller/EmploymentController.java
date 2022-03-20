package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Employment;
import org.example.application.custom.form.EmploymentForm;
import org.example.application.custom.service.EmploymentService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/employment/")
public class EmploymentController extends BaseController {
    private final EmploymentService employmentService;

    public EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @GetMapping("/list")
    public ResponseDTO getList() {
        log.info("收到请求->获取就业列表");
        List<Employment> list = employmentService.getAll();
        log.info("返回结果->获取就业列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @GetMapping("/search/{keywords}")
    public ResponseDTO search(@PathVariable String keywords) {
        log.info("收到请求->搜索就业列表,keywords:[{}]",keywords);
        List<Employment> list = employmentService.search(keywords);
        log.info("返回结果->搜索就业列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody EmploymentForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布就业:[{}]",form);
        Employment entity = employmentService.save(request,form);
        log.info("返回结果->发布就业结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @GetMapping("/mine")
    public ResponseDTO getListByMine(HttpServletRequest request) {
        log.info("收到请求->获取我发布的就业列表");
        List<Employment> list = employmentService.getListByMine(request);
        log.info("返回结果->获取我发布的就业列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        log.info("收到请求->删除我发布的就业,id:[{}]",id);
        employmentService.deleteById(id);
        log.info("返回结果->删除我发布的就业结束");
        return ResponseDTO.returnSuccess();
    }

    @PostMapping("/checkPass")
    public ResponseDTO checkPass(@RequestBody EmploymentForm form) {
        log.info("收到请求->就业通过审核[{}]",form);
        employmentService.checkPass(form);
        log.info("返回结果->就业通过审核结束");
        return ResponseDTO.returnSuccess();
    }
    @PostMapping("/checkFail")
    public ResponseDTO checkFail(@RequestBody EmploymentForm form) {
        log.info("收到请求->就业驳回审核[{}]",form);
        employmentService.checkFail(form);
        log.info("返回结果->就业驳回审核结束");
        return ResponseDTO.returnSuccess();
    }
}

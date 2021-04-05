package cn.crabapples.application.controller;

import cn.crabapples.application.entity.Subject;
import cn.crabapples.application.form.SubjectForm;
import cn.crabapples.application.service.SubjectService;
import cn.crabapples.common.BaseController;
import cn.crabapples.common.groups.IsLogin;
import cn.crabapples.common.utils.jwt.JwtIgnore;
import cn.crabapples.system.dto.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@RestController
@Api("系统管理")
@Slf4j
@RequestMapping("/api/subject")
public class SubjectController extends BaseController {

    private final cn.crabapples.application.service.SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @JwtIgnore
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存课题接口")
    public ResponseDTO save(@RequestBody SubjectForm form) {
        log.info("收到请求->保存课题:[{}]", form);
        super.validator(form, IsLogin.class);
        subjectService.saveSubject(form);
        log.info("返回结果->保存课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @JwtIgnore
    @GetMapping("/list")
    @ApiOperation(value = "获取列表", notes = "获取课题列表接口")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Subject> resultList = subjectService.getAll();
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

}

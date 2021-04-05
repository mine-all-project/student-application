package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.common.utils.jwt.JwtIgnore;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.service.SubjectService;
import cn.crabapples.application.system.dto.ResponseDTO;
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
@Slf4j
@RequestMapping("/api/subject")
public class SubjectController extends BaseController {

    private final cn.crabapples.application.custom.service.SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @JwtIgnore
    @PostMapping("/save")
    public ResponseDTO save(@RequestBody SubjectForm form) {
        log.info("收到请求->保存课题:[{}]", form);
        super.validator(form, IsLogin.class);
        subjectService.saveSubject(form);
        log.info("返回结果->保存课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @JwtIgnore
    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Subject> resultList = subjectService.getAll();
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

}

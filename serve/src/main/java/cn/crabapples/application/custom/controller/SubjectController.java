package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import cn.crabapples.application.common.utils.jwt.JwtIgnore;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.Subject$Step$ResultInfoForm;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.service.SubjectService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseDTO save(HttpServletRequest request, @RequestBody SubjectForm form) {
        log.info("收到请求->保存课题:[{}]", form);
        super.validator(form, IsAdd.class);
        subjectService.saveSubject(request, form);
        log.info("返回结果->保存课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @JwtIgnore
    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取课题列表");
        List<Subject> resultList = subjectService.getAll();
        log.info("返回结果->获取课题列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @JwtIgnore
    @GetMapping("/end/{id}")
    public ResponseDTO endById(@PathVariable String id) {
        log.info("收到请求->结束当前课题:[{}]", id);
        subjectService.endSubjectById(id);
        log.info("返回结果->结束当前课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }


    @JwtIgnore
    @GetMapping("/step/list/{subjectId}")
    public ResponseDTO stepList(@PathVariable String subjectId) {
        log.info("收到请求->获取阶段列表");
        List<Subject.Step> resultList = subjectService.getStepList(subjectId);
        log.info("返回结果->获取阶段列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @JwtIgnore
    @GetMapping("/step/end/{id}")
    public ResponseDTO endStepById(@PathVariable String id) {
        log.info("收到请求->结束当前阶段:[{}]", id);
        subjectService.endStepById(id);
        log.info("返回结果->结束当前阶段完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @JwtIgnore
    @PostMapping("/result-info/save")
    public ResponseDTO saveResultInfo(@RequestBody Subject$Step$ResultInfoForm form) {
        log.info("收到请求->保存成果:[{}]", form);
        if (form.isAdd()) {
            super.validator(form, IsAdd.class);
        } else if (form.isEdit()) {
            super.validator(form, IsEdit.class);
        }
        subjectService.saveResultInfo(form);
        log.info("返回结果->保存成果完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

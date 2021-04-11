package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.form.SubjectStepResultInfoForm;
import cn.crabapples.application.custom.service.RequestPullService;
import cn.crabapples.application.custom.service.SubjectService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:52
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */

@RestController
@Slf4j
@RequestMapping("/api/subject")
public class SubjectController extends BaseController {

    private final SubjectService subjectService;
    private final RequestPullService requestPullService;

    public SubjectController(SubjectService subjectService,
                             RequestPullService requestPullService) {
        this.subjectService = subjectService;
        this.requestPullService = requestPullService;
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody SubjectForm form) {
        log.info("收到请求->保存课题:[{}]", form);
        super.validator(form, IsAdd.class);
        subjectService.saveSubject(request, form);
        log.info("返回结果->保存课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取课题列表");
        List<Subject> resultList = subjectService.getAll();
        log.info("返回结果->获取课题列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/mine-list")
    public ResponseDTO mineList(HttpServletRequest request) {
        log.info("收到请求->获取我的课题列表");
        List<Subject> resultList = subjectService.getMineAll(request);
        log.info("返回结果->获取我的课题列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/mine-join")
    public ResponseDTO mineJoin(HttpServletRequest request) {
        log.info("收到请求->获取我参与的课题列表");
        List<Subject> resultList = subjectService.getMineJoin(request);
        log.info("返回结果->获取我参与的课题列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/audit-list")
    public ResponseDTO audit(HttpServletRequest request) {
        log.info("收到请求->获取待审核课题列表");
        List<Subject> resultList = subjectService.getAuditList(request);
        log.info("返回结果->获取待审核列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/audit/accept/{id}")
    public ResponseDTO accept(@PathVariable String id) {
        log.info("收到请求->通过审核:[{}]", id);
        subjectService.accept(id);
        log.info("返回结果->通过审核完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/audit/unaccepted/{id}")
    public ResponseDTO unaccepted(@PathVariable String id) {
        log.info("收到请求->驳回审核:[{}]", id);
        subjectService.unaccepted(id);
        log.info("返回结果->驳回审核完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/mine-pull")
    public ResponseDTO minePull(HttpServletRequest request) {
        log.info("收到请求->获取我的收到的共享列表");
        List<SubjectShare> resultList = subjectService.getMinePull(request);
        log.info("返回结果->获取我的收到的共享列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/end/{id}")
    public ResponseDTO endById(@PathVariable String id) {
        log.info("收到请求->结束当前课题:[{}]", id);
        subjectService.endSubjectById(id);
        log.info("返回结果->结束当前课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/share/{id}")
    public ResponseDTO shareById(@PathVariable String id, HttpServletRequest request) {
        log.info("收到请求->共享当前课题:[{}]", id);
        subjectService.shareById(id, request);
        log.info("返回结果->共享当前课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/close-share/{id}")
    public ResponseDTO closeShareById(@PathVariable String id) {
        log.info("收到请求->取消共享当前课题:[{}]", id);
        subjectService.closeShareById(id);
        log.info("返回结果->取消共享当前课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/step/end/{id}")
    public ResponseDTO endStepById(@PathVariable String id) {
        log.info("收到请求->结束当前阶段:[{}]", id);
        subjectService.endStepById(id);
        log.info("返回结果->结束当前阶段完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @PostMapping("/result-info/save")
    public ResponseDTO saveResultInfo(@RequestBody SubjectStepResultInfoForm form) {
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

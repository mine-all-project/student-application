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

    @GetMapping("/mine-pull")
    public ResponseDTO minePull(HttpServletRequest request) {
        log.info("收到请求->获取我的收到的共享列表");
        List<SubjectShare> resultList = subjectService.getMinePull(request);
        log.info("返回结果->获取我的收到的共享列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/request-pull/{id}")
    public ResponseDTO requestPull(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->发起浏览申请,id:[{}]", id);
        requestPullService.requestPull(request, id);
        log.info("返回结果->发起浏览申请完成");
        return ResponseDTO.returnSuccess("操作成功");
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

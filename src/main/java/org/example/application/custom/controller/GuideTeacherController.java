package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.GuideForm;
import org.example.application.custom.service.GuideService;
import org.example.application.custom.service.SubjectService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/guide")
public class GuideTeacherController extends BaseController {
    private final SubjectService subjectService;
    private final GuideService guideService;

    public GuideTeacherController(SubjectService subjectService, GuideService guideService) {
        this.subjectService = subjectService;
        this.guideService = guideService;
    }

    @PostMapping("/save")
    @JwtIgnore
    public ResponseDTO saveGuideTeacher(HttpServletRequest request, @RequestBody GuideForm form) {
        log.info("收到请求->保存指导教师表:[{}]", form);
        Subject entity = guideService.saveGuideTeacher(request,form);
        log.info("返回结果->保存指导教师表->结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }


}

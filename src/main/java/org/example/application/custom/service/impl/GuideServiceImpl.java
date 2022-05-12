package org.example.application.custom.service.impl;

import org.example.application.custom.dao.GuideDAO;
import org.example.application.custom.dao.SubjectDAO;
import org.example.application.custom.entity.GuideTeacher;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.GuideForm;
import org.example.application.custom.service.GuideService;
import org.example.application.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class GuideServiceImpl implements GuideService {
    private final SystemService systemService;
    private final SubjectDAO subjectDAO;
    private final GuideDAO guideDAO;

    public GuideServiceImpl(SystemService systemService, SubjectDAO subjectDAO, GuideDAO guideDAO) {
        this.systemService = systemService;
        this.subjectDAO = subjectDAO;
        this.guideDAO = guideDAO;
    }

    @Override
    public Subject saveGuideTeacher(HttpServletRequest request, GuideForm form) {
        Subject entity = subjectDAO.findById(form.getId());
        entity.setReviewer(form.getReviewer());
        GuideTeacher guideTeacher = new GuideTeacher();
        guideTeacher.setContent(form.getContent());
        guideTeacher.setTeacher(systemService.getUserInfo(request));
        entity.setGuideTeacher(guideDAO.save(guideTeacher));
        return subjectDAO.save(entity);
    }
}

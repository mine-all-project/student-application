package org.example.application.custom.service.impl;

import org.example.application.custom.dao.GuideDAO;
import org.example.application.custom.dao.StatusDAO;
import org.example.application.custom.dao.SubjectDAO;
import org.example.application.custom.entity.GuideTeacher;
import org.example.application.custom.entity.Status;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.GuideForm;
import org.example.application.custom.service.GuideService;
import org.example.application.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class GuideServiceImpl implements GuideService {
    private final SystemService systemService;
    private final SubjectDAO subjectDAO;
    private final GuideDAO guideDAO;
    private final StatusDAO statusDAO;

    public GuideServiceImpl(SystemService systemService, SubjectDAO subjectDAO, GuideDAO guideDAO, StatusDAO statusDAO) {
        this.systemService = systemService;
        this.subjectDAO = subjectDAO;
        this.guideDAO = guideDAO;
        this.statusDAO = statusDAO;
    }

    @Override
    public Subject saveGuideTeacher(HttpServletRequest request, GuideForm form) {
        Subject entity = subjectDAO.findById(form.getId());
        entity.setReviewer(form.getReviewer());
        GuideTeacher guideTeacher = new GuideTeacher();
        guideTeacher.setContent(form.getContent());
        guideTeacher.setTeacher(systemService.getUserInfo(request));
        entity.setGuideTeacher(guideDAO.save(guideTeacher));
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status lastStatus = statusList.get(0);
        statusList.add(lastStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }
}

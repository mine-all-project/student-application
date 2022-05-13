package org.example.application.custom.service.impl;

import org.example.application.custom.dao.GuideDAO;
import org.example.application.custom.dao.ReviewDAO;
import org.example.application.custom.dao.StatusDAO;
import org.example.application.custom.dao.SubjectDAO;
import org.example.application.custom.entity.ReviewTeacher;
import org.example.application.custom.entity.Status;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.ReviewerForm;
import org.example.application.custom.service.ReviewService;
import org.example.application.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final SystemService systemService;
    private final SubjectDAO subjectDAO;
    private final GuideDAO guideDAO;
    private final StatusDAO statusDAO;
    private final ReviewDAO reviewDAO;

    public ReviewServiceImpl(SystemService systemService, SubjectDAO subjectDAO, GuideDAO guideDAO, StatusDAO statusDAO, ReviewDAO reviewDAO) {
        this.systemService = systemService;
        this.subjectDAO = subjectDAO;
        this.guideDAO = guideDAO;
        this.statusDAO = statusDAO;
        this.reviewDAO = reviewDAO;
    }

    @Override
    public Subject saveReviewTeacher(HttpServletRequest request, ReviewerForm form) {
        Subject entity = subjectDAO.findById(form.getId());
        ReviewTeacher reviewTeacher = new ReviewTeacher();
        reviewTeacher.setContent(form.getContent());
        reviewTeacher.setTeacher(systemService.getUserInfo(request));
        entity.setReviewTeacher(reviewDAO.save(reviewTeacher));
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status lastStatus = statusList.get(0);
        statusList.add(lastStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }
}

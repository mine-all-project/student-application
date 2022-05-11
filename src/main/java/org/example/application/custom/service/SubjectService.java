package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.CheckForm;
import org.example.application.custom.form.StatusForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SubjectService extends BaseService {
    Subject saveSubject(HttpServletRequest request, Subject entity);

    List<Subject> getAll();

    List<Subject> getAll(String status);

    Subject checkSubject(HttpServletRequest request, CheckForm form);

    Subject editStatusContent(HttpServletRequest request, StatusForm form);

    Subject mineSubject(HttpServletRequest request);

    Subject selectSubject(HttpServletRequest request);

    Subject selectSubject(HttpServletRequest request, String id);

}

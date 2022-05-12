package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.GuideForm;

import javax.servlet.http.HttpServletRequest;

public interface GuideService extends BaseService {
    Subject saveGuideTeacher(HttpServletRequest request, GuideForm form);
}

package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.ReviewerForm;

import javax.servlet.http.HttpServletRequest;

public interface ReviewService extends BaseService {
    Subject saveReviewTeacher(HttpServletRequest request, ReviewerForm form);
}

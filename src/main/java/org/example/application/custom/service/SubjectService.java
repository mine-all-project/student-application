package org.example.application.custom.service;

import com.itextpdf.text.DocumentException;
import org.example.application.common.BaseService;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.CheckForm;
import org.example.application.custom.form.StatusForm;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface SubjectService extends BaseService {
    Subject saveSubject(HttpServletRequest request, Subject entity);

    void deleteSubject(HttpServletRequest request, String id);

    List<Subject> getAll();

    List<Subject> getAll(String status);

    List<Subject> searchSubject(String status, String keyword);

    Subject checkSubject(HttpServletRequest request, CheckForm form);

    Subject editStatusContent(HttpServletRequest request, StatusForm form);

    Subject mineSubject(HttpServletRequest request);

    Subject selectSubject(HttpServletRequest request);

    Subject selectSubject(HttpServletRequest request, String id);

    Subject editFirstContent(HttpServletRequest request, StatusForm form);

    Subject checkFirstContent(HttpServletRequest request, CheckForm form);

    Subject editLastContent(HttpServletRequest request, StatusForm form);

    Subject createTable(HttpServletRequest request, Subject form) throws DocumentException, IOException;

    Subject createEndTable(HttpServletRequest request, Subject form) throws DocumentException, IOException;

    Subject isGood(HttpServletRequest request, String id, String status);

    Subject saveTwoTime(HttpServletRequest request, Subject form);
}

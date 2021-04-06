package cn.crabapples.application.custom.service;

import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.Subject$Step$ResultInfoForm;
import cn.crabapples.application.custom.form.SubjectForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SubjectService {
    Subject saveSubject(HttpServletRequest request, SubjectForm form);

    List<Subject> getAll();

    List<Subject.Step> getStepList(String subjectId);

    void saveResultInfo(Subject$Step$ResultInfoForm form);

    void endStepById(String id);

    void endSubjectById(String id);
}

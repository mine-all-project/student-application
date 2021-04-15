package cn.crabapples.application.custom.service;

import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.form.SubjectStepResultInfoForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 科研项目功能抽象接口
 */
public interface SubjectService extends BaseService {
    Subject saveSubject(HttpServletRequest request, SubjectForm form);

    List<Subject> getAll();

    List<Subject> getMineAll(HttpServletRequest request);

    void saveResultInfo(SubjectStepResultInfoForm form);

    void endStepById(String id);

    void endSubjectById(String id);

    void shareById(String id,HttpServletRequest request);

    void closeShareById(String id);

    List<SubjectShare> getMinePull(HttpServletRequest request);

    List<Subject> getMineJoin(HttpServletRequest request);

    List<Subject> getAuditList(HttpServletRequest request);

    Subject accept(String id);

    Subject unaccepted(String id);
}

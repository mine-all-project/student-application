package cn.crabapples.application.custom.service;

import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.SubjectForm;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(SubjectForm form);

    List<Subject> getAll();
}

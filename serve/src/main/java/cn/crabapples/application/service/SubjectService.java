package cn.crabapples.application.service;

import cn.crabapples.application.entity.Subject;
import cn.crabapples.application.form.SubjectForm;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(SubjectForm form);

    List<Subject> getAll();
}

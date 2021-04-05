package cn.crabapples.system.service;

import cn.crabapples.system.entity.Subject;
import cn.crabapples.system.form.SubjectForm;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(SubjectForm form);

    List<Subject> getAll();
}

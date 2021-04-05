package cn.crabapples.application.custom.dao;

import cn.crabapples.application.custom.dao.jpa.Subject$StepRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectRepository;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.SubjectForm;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO {
    private final SubjectRepository subjectRepository;
    private final Subject$StepRepository subject$StepRepository;

    public SubjectDAO(SubjectRepository subjectRepository, Subject$StepRepository subject$StepRepository) {
        this.subjectRepository = subjectRepository;
        this.subject$StepRepository = subject$StepRepository;
    }


    public Subject save(SubjectForm form) {
        Subject entity = new Subject();
        BeanUtils.copyProperties(form, entity);
        return save(entity);
    }

    public Subject save(Subject entity) {
        this.subject$StepRepository.saveAll(entity.getStepList());
        return subjectRepository.saveAndFlush(entity);
    }

    public List<Subject> getAll() {
        Sort sort = Sort.by("createTime").descending();
        return subjectRepository.findAll(sort);
    }
}

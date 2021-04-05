package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.Subject$StepRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectRepository;
import cn.crabapples.application.custom.dao.jpa.TagsRepository;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.SubjectForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;
    private final Subject$StepRepository subject$StepRepository;
    private final TagsRepository tagsRepository;

    public SubjectDAO(SubjectRepository subjectRepository,
                      Subject$StepRepository subject$StepRepository,
                      TagsRepository tagsRepository) {
        this.subjectRepository = subjectRepository;
        this.subject$StepRepository = subject$StepRepository;
        this.tagsRepository = tagsRepository;
    }


    public Subject save(SubjectForm form) {
        Subject entity = new Subject();
        BeanUtils.copyProperties(form, entity);
        setSteps(entity);
        setTags(form, entity);
        return save(entity);
    }

    private void setSteps(Subject entity) {
        List<Subject.Step> steps = entity.getStepList();
        System.err.println(steps);
        subject$StepRepository.saveAll(entity.getStepList());

//        List<Subject.Step> steps = entity.getStepList();
//        List<Subject.Step> stepList = new ArrayList<>(steps.size());
//        steps.forEach(e -> {
//            subject$StepRepository.saveAndFlush(e);
//            stepList.add(e);
//        });
//        System.err.println(666);
//        entity.setStepList(stepList);
    }

    private void setTags(SubjectForm form, Subject entity) {
        List<Tags> tags = tagsRepository.findByIdInAndDelFlag(form.getTagList(), NOT_DEL);
        entity.setTags(tags);
    }


    public Subject save(Subject entity) {
        return subjectRepository.saveAndFlush(entity);
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll(desByCreateTime);
    }
}

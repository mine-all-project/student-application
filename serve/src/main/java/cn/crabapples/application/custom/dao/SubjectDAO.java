package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.Subject$Step$ResultInfoRepository;
import cn.crabapples.application.custom.dao.jpa.Subject$StepRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectRepository;
import cn.crabapples.application.custom.dao.jpa.TagsRepository;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.Subject$Step$ResultInfoForm;
import cn.crabapples.application.custom.form.SubjectForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;
    private final Subject$StepRepository subject$StepRepository;
    private final Subject$Step$ResultInfoRepository subject$Step$ResultInfoRepository;
    private final TagsRepository tagsRepository;

    public SubjectDAO(SubjectRepository subjectRepository,
                      Subject$StepRepository subject$StepRepository,
                      Subject$Step$ResultInfoRepository subject$Step$ResultInfoRepository, TagsRepository tagsRepository) {
        this.subjectRepository = subjectRepository;
        this.subject$StepRepository = subject$StepRepository;
        this.subject$Step$ResultInfoRepository = subject$Step$ResultInfoRepository;
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
        subject$StepRepository.saveAll(entity.getStepList());
    }

    private void setTags(SubjectForm form, Subject entity) {
        List<Tags> tags = tagsRepository.findByIdInAndDelFlag(form.getTagsList(), NOT_DEL);
        entity.setTags(tags);
    }


    public Subject save(Subject entity) {
        return subjectRepository.saveAndFlush(entity);
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll(desByCreateTime);
    }

    public List<Subject.Step> getStepList(String subjectId) {
        throw new ApplicationException("暂未实现");
    }

    public void saveResultInfo(Subject$Step$ResultInfoForm form) {
        System.err.println(form);
        Subject.Step step = subject$StepRepository.findByIdAndDelFlag(form.getStepId(), NOT_DEL);
        AssertUtils.notNull(step, "操作失败");
        if (step.getStatus() == 1) {
            throw new ApplicationException("当前阶段状态异常");
        }
        Subject.Step.ResultInfo entity = new Subject.Step.ResultInfo();
        BeanUtils.copyProperties(form, entity);
        entity = subject$Step$ResultInfoRepository.saveAndFlush(entity);
        if (form.isAdd()) {
            List<Subject.Step.ResultInfo> resultInfos = step.getResultInfos();
            resultInfos.add(entity);
            step.setResultInfos(resultInfos);
            subject$StepRepository.saveAndFlush(step);
        }
    }

    private void checkStepStatus() {

    }

    public void endStepById(String id) {
        Subject.Step step = subject$StepRepository.findByIdAndDelFlag(id, NOT_DEL);
        step.setStatus(1);
        subject$StepRepository.saveAndFlush(step);
    }

    public void endSubjectById(String id) {
        Subject subject = subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
        subject.setStatus(1);
        subjectRepository.saveAndFlush(subject);
    }
}

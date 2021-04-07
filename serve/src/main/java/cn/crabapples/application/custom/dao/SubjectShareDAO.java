package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.SubjectRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectStepRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectStepResultInfoRepository;
import cn.crabapples.application.custom.dao.jpa.TagsRepository;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectStep;
import cn.crabapples.application.custom.entity.SubjectStepResultInfo;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.form.SubjectStepResultInfoForm;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SubjectShareDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;
    private final SubjectStepRepository subjectStepRepository;
    private final SubjectStepResultInfoRepository subjectStepResultInfoRepository;
    private final TagsRepository tagsRepository;
    private final UserDAO userDAO;

    public SubjectShareDAO(SubjectRepository subjectRepository,
                           SubjectStepRepository subjectStepRepository,
                           SubjectStepResultInfoRepository subjectStepResultInfoRepository,
                           TagsRepository tagsRepository, UserDAO userDAO) {
        this.subjectRepository = subjectRepository;
        this.subjectStepRepository = subjectStepRepository;
        this.subjectStepResultInfoRepository = subjectStepResultInfoRepository;
        this.tagsRepository = tagsRepository;
        this.userDAO = userDAO;
    }


    public Subject save(SubjectForm form) {
        Subject entity = new Subject();
        BeanUtils.copyProperties(form, entity);
        setPerson(form, entity);
        setSteps(entity);
        setTags(form, entity);
        setOtherData(entity);
        return save(entity);
    }

    private void setOtherData(Subject entity) {
        entity.setStatus(0);
        entity.setIsShare(1);
    }


    private void setPerson(SubjectForm form, Subject entity) {
        entity.setPersonList(userDAO.findByIds(form.getPersonList()));
    }

    private void setSteps(Subject entity) {
        subjectStepRepository.saveAll(entity.getStepList());
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

    public void saveResultInfo(SubjectStepResultInfoForm form) {
        SubjectStep step = subjectStepRepository.findByIdAndDelFlag(form.getStepId(), NOT_DEL);
        AssertUtils.notNull(step, "操作失败");
        if (step.getStatus() == 1) {
            throw new ApplicationException("当前阶段状态异常");
        }
        SubjectStepResultInfo entity = new SubjectStepResultInfo();
        BeanUtils.copyProperties(form, entity);
        entity = subjectStepResultInfoRepository.saveAndFlush(entity);
        if (form.isAdd()) {
            List<SubjectStepResultInfo> resultInfos = step.getResultInfos();
            resultInfos.add(entity);
            step.setResultInfos(resultInfos);
            subjectStepRepository.saveAndFlush(step);
        }
    }

    public void endStepById(String id) {
        SubjectStep step = subjectStepRepository.findByIdAndDelFlag(id, NOT_DEL);
        AssertUtils.notNull(step, "操作失败");
        step.setStatus(1);
        subjectStepRepository.saveAndFlush(step);
    }

    public void endSubjectById(String id) {
        Subject subject = subjectRepository.findByIdAndStatusAndDelFlag(id, 1, NOT_DEL);
        AssertUtils.notNull(subject, "操作失败");
        subject.setStatus(2);
        subject.setEndTime(LocalDateTime.now());
        subjectRepository.saveAndFlush(subject);
    }

    public List<Subject> getMineAll(SysUser createBy) {
        return subjectRepository.findByCreateByAndDelFlag(desByCreateTime, createBy, NOT_DEL);
    }

    public void shareById(String id) {
        Subject subject = subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
        subject.setIsShare(0);
        subjectRepository.saveAndFlush(subject);
    }

    public void closeShareById(String id) {
        Subject subject = subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
        subject.setIsShare(1);
        subjectRepository.saveAndFlush(subject);
    }

    public List<Subject> getMinePull(SysUser createBy) {
        List<Subject> subjects = subjectRepository.findByCreateByNotAndDelFlagAndIsShare(desByCreateTime, createBy, NOT_DEL, 0);
        subjects.forEach(System.err::println);
        return subjects;
    }

}

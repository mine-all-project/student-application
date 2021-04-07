package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.*;
import cn.crabapples.application.custom.entity.*;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.form.SubjectStepResultInfoForm;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;
    private final SubjectStepRepository subjectStepRepository;
    private final SubjectStepResultInfoRepository subjectStepResultInfoRepository;
    private final SubjectShareRepository subjectShareRepository;
    private final TagsRepository tagsRepository;
    private final UserDAO userDAO;

    public SubjectDAO(SubjectRepository subjectRepository,
                      SubjectStepRepository subjectStepRepository,
                      SubjectStepResultInfoRepository subjectStepResultInfoRepository,
                      SubjectShareRepository subjectShareRepository,
                      TagsRepository tagsRepository, UserDAO userDAO) {
        this.subjectRepository = subjectRepository;
        this.subjectStepRepository = subjectStepRepository;
        this.subjectStepResultInfoRepository = subjectStepResultInfoRepository;
        this.subjectShareRepository = subjectShareRepository;
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

    public List<Subject> getMineJoin(SysUser sysUser) {
        return subjectRepository.findByPersonListContainsAndDelFlag(desByCreateTime, sysUser, NOT_DEL);
    }

    public void shareById(String id, SysUser sysUser) {
        Subject subject = subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
        subject.setIsShare(0);
        subjectRepository.saveAndFlush(subject);
        SubjectShare share = subjectShareRepository.findBySubjectAndStatus(subject, 0).orElse(new SubjectShare());
        share.setSubject(subject);
        share.setStatus(0);
        share.setShareBy(sysUser);
        subjectShareRepository.saveAndFlush(share);
    }

    public void closeShareById(String id) {
        Subject subject = subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
        SubjectShare share = subjectShareRepository.findBySubjectAndStatus(subject, 1).get();
        AssertUtils.notNull(share, "分享状态异常");
        subject.setIsShare(1);
        share.setStatus(1);
        subjectShareRepository.saveAndFlush(share);
        subjectRepository.saveAndFlush(subject);
    }

    public List<SubjectShare> getMinePull(SysUser createBy) {
//        List<SubjectShare> share = subjectShareRepository.findByStatusAndShareByNot(0, createBy);
        List<SubjectShare> share = subjectShareRepository.findByStatus(0);
        share.forEach((e) -> {
            List<SysUserDTO> shareUsers = new ArrayList<>();
            e.getShareUser().forEach(r -> {
                SysUserDTO shareUser = new SysUserDTO();
                BeanUtils.copyProperties(r, shareUser);
                shareUsers.add(shareUser);
            });
            SysUserDTO shareBy = new SysUserDTO();
            BeanUtils.copyProperties(e.getShareBy(), shareBy);
            e.setShareByUser(shareBy);
            e.setShareUserList(shareUsers);
        });
        return share;
    }

    public Subject findById(String id) {
        return subjectRepository.findByIdAndDelFlag(id, NOT_DEL);
    }


}

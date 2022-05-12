package org.example.application.custom.service.impl;

import org.example.application.common.ApplicationException;
import org.example.application.common.DIC;
import org.example.application.common.utils.AssertUtils;
import org.example.application.custom.dao.StatusDAO;
import org.example.application.custom.dao.SubjectDAO;
import org.example.application.custom.entity.Status;
import org.example.application.custom.entity.Subject;
import org.example.application.custom.form.CheckForm;
import org.example.application.custom.form.StatusForm;
import org.example.application.custom.service.SubjectService;
import org.example.application.system.entity.SystemUser;
import org.example.application.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class SubjectServiceImpl implements SubjectService {
    private final SystemService systemService;
    private final SubjectDAO subjectDAO;
    private final StatusDAO statusDAO;


    public SubjectServiceImpl(SystemService systemService, SubjectDAO subjectDAO, StatusDAO statusDAO) {
        this.systemService = systemService;
        this.subjectDAO = subjectDAO;
        this.statusDAO = statusDAO;
    }

    @Override
    public Subject saveSubject(HttpServletRequest request, Subject entity) {
        entity.setCreateBy(systemService.getUserInfo(request));
        List<Status> statusList = new LinkedList<>();
        statusList.add(new Status(Status.Code.ZERO));
        entity.setStatus(statusDAO.save(statusList));
        entity.setIsSelect(DIC.NOT_SELECT);
        return subjectDAO.save(entity);
    }

    @Override
    public void deleteSubject(HttpServletRequest request, String id) {
        subjectDAO.deleteById(id);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDAO.getList();
    }

    @Override
    public List<Subject> getAll(String code) {
        Status.Code status = Status.Code.getByCode(code);
        List<Subject> list = subjectDAO.getList();
        return list.stream().filter(e -> {
            List<Status> statusList = e.getStatus();
            statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
            if (!statusList.isEmpty()) {
                Status lastStatus = statusList.get(0);
                if (lastStatus.getCode() == status) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Subject> searchSubject(String code, String keyword) {
        Status.Code status = Status.Code.getByCode(code);
        List<Subject> list = subjectDAO.getListByName(keyword);
        return list.stream().filter(e -> {
            List<Status> statusList = e.getStatus();
            statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
            if (!statusList.isEmpty()) {
                Status lastStatus = statusList.get(0);
                if (lastStatus.getCode() == status) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * 系主任审核
     */
    @Override
    public Subject checkSubject(HttpServletRequest request, CheckForm form) {
        Subject entity = subjectDAO.findById(form.getId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status lastStatus = statusList.get(0);
        statusList.remove(0);
        lastStatus.setContent(form.getContent());
        statusList.add(lastStatus);
        Status newStatus;
        if (DIC.CHECK_STATUS_PASS == form.getStatus()) {
            newStatus = lastStatus.next();
        } else {
            newStatus = lastStatus.close(form.getContent());
        }
        statusList.add(newStatus);
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /**
     * 编写任务
     */
    @Override
    public Subject editStatusContent(HttpServletRequest request, StatusForm form) {
        Subject entity = subjectDAO.findById(form.getSubjectId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusDAO.findById(form.getId());
        statusList.remove(0);
        Status lastStatus = statusList.get(0);
        if (lastStatus.getCode() == Status.Code.ONE) {
            statusList.remove(0);
        }
        nowStatus.setContent(form.getContent());
        statusList.add(nowStatus);
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    @Override
    public Subject mineSubject(HttpServletRequest request) {
        SystemUser user = systemService.getUserInfo(request);
        return subjectDAO.findBySelecter(user);
    }

    /**
     * 选择课题(随机)
     */
    @Override
    public Subject selectSubject(HttpServletRequest request) {
        Subject subject = mineSubject(request);
        AssertUtils.isNull(subject, DIC.HAS_SELECT_SUBJECT);
        List<Subject> subjectList = subjectDAO.getList().stream().filter(e -> DIC.NOT_SELECT == e.getIsSelect()).collect(Collectors.toList());
        int size = subjectList.size();
        if (size == DIC.ZERO) {
            throw new ApplicationException("暂无可选课题");
        }
        if (size == 1) {
            size = 0;
        }
        int index = new Random().nextInt(size + 1);
        Subject entity = subjectList.get(index);
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status lastStatus = statusList.get(0);
        lastStatus = lastStatus.next();
        statusList.add(lastStatus);
        entity.setStatus(statusDAO.save(statusList));
        entity.setIsSelect(DIC.IS_SELECT);
        entity.setSelecter(systemService.getUserInfo(request));
        return subjectDAO.save(entity);
    }

    /**
     * 选择课题(定向)
     */
    @Override
    public Subject selectSubject(HttpServletRequest request, String id) {
        Subject subject = mineSubject(request);
        AssertUtils.isNull(subject, DIC.HAS_SELECT_SUBJECT);
        Subject entity = subjectDAO.findById(id);
        entity.setIsSelect(DIC.IS_SELECT);
        entity.setSelecter(systemService.getUserInfo(request));
        return subjectDAO.save(entity);
    }

    /**
     * 撰写一稿
     */
    @Override
    public Subject editFirstContent(HttpServletRequest request, StatusForm form) {
        Subject entity = subjectDAO.findById(form.getSubjectId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusDAO.findById(form.getId());
        statusList.remove(0);
        Status lastStatus = statusList.get(0);
        if (lastStatus.getCode() == Status.Code.THREE) {
            statusList.remove(0);
        }
        nowStatus.setContent(form.getContent());
        statusList.add(nowStatus);
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /**
     * 中期检查
     */
    @Override
    public Subject checkFirstContent(HttpServletRequest request, CheckForm form) {
        Subject entity = subjectDAO.findById(form.getId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status lastStatus = statusList.get(0);
        statusList.remove(0);
        lastStatus.setContent(form.getContent());
        statusList.add(lastStatus);
        Status newStatus;
        if (DIC.CHECK_STATUS_PASS == form.getStatus()) {
            newStatus = lastStatus.next();
            statusList.add(newStatus);
        }
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /**
     * 撰写终稿
     */
    @Override
    public Subject editLastContent(HttpServletRequest request, StatusForm form) {
        Subject entity = subjectDAO.findById(form.getSubjectId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusDAO.findById(form.getId());
        statusList.remove(0);
        Status lastStatus = statusList.get(0);
        if (lastStatus.getCode() == Status.Code.FIVE) {
            statusList.remove(0);
        }
        nowStatus.setContent(form.getContent());
        statusList.add(nowStatus);
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }
}

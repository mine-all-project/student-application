package org.example.application.custom.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${uploadPath}")
    private String uploadPath;
    private final SystemService systemService;
    private final SubjectDAO subjectDAO;
    private final StatusDAO statusDAO;
    private final TemplateEngine engine;


    public SubjectServiceImpl(SystemService systemService, SubjectDAO subjectDAO, StatusDAO statusDAO,
                              TemplateEngine engine) {
        this.systemService = systemService;
        this.subjectDAO = subjectDAO;
        this.statusDAO = statusDAO;
        this.engine = engine;
    }

    /*添加*/
    @Override
    public Subject saveSubject(HttpServletRequest request, Subject entity) {
        entity.setCreateBy(systemService.getUserInfo(request));
        List<Status> statusList = new LinkedList<>();
        statusList.add(new Status(Status.Code.ZERO));
        entity.setStatus(statusDAO.save(statusList));
        entity.setIsSelect(DIC.NOT_SELECT);
        return subjectDAO.save(entity);
    }

    /*删除*/
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
                for (Status status1 : statusList) {
                    if (status1.getCode() == Status.Code.ERROR) {
                        return false;
                    }
                }
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

    /*系主任审核*/
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

    /*编写任务*/
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

    /*选择课题(随机)*/
    @Override
    public Subject selectSubject(HttpServletRequest request) {
        Subject subject = mineSubject(request);
        AssertUtils.isNull(subject, DIC.HAS_SELECT_SUBJECT);
        List<Subject> subjectList = subjectDAO.getList().stream().filter(e -> {
            if (DIC.NOT_SELECT != e.getIsSelect()) {
                return false;
            }
            List<Status> statusList = e.getStatus();
            statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
            Status lastStatus = statusList.get(0);
            return lastStatus.getCode().getCode() == Status.Code.TWO.getCode();
        }).collect(Collectors.toList());
        int size = subjectList.size();
        if (size == DIC.ZERO) {
            throw new ApplicationException("暂无可选课题");
        }
        int index = new Random().nextInt(size);
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


    /*选择课题(定向，未使用)*/
    @Override
    public Subject selectSubject(HttpServletRequest request, String id) {
        Subject subject = mineSubject(request);
        AssertUtils.isNull(subject, DIC.HAS_SELECT_SUBJECT);
        Subject entity = subjectDAO.findById(id);
        entity.setIsSelect(DIC.IS_SELECT);
        entity.setSelecter(systemService.getUserInfo(request));
        return subjectDAO.save(entity);
    }

    /*撰写一稿*/
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

    /*中期检查*/
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

    /*撰写终稿*/
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

    /*生成答辩信息表*/
    @Override
    public Subject createTable(HttpServletRequest request, Subject form) throws DocumentException, IOException {
        Subject entity = subjectDAO.findById(form.getId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusList.get(0);
        entity.setAddress(form.getAddress());
        entity.setTime(form.getTime());
        entity.setGroups(form.getGroups());
        Map<String, Object> dataMap = createPaperDataMap(entity);
        entity.setPaperPdfUrl(createPdf("table", dataMap));
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /*准备生成答辩现场表pdf所需数据*/
    private Map<String, Object> createPaperDataMap(Subject source) {
        List<Status> statusList = source.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        String content = "";
        for (Status status : statusList) {
            if (status.getCode() == Status.Code.FIVE) {
                content = status.getContent();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("subjectName", source.getName());
        map.put("subjectSelector", source.getSelecter().getName());
        map.put("guideTeacher", source.getGuideTeacher().getTeacher().getName());
        map.put("reviewTeacher", source.getReviewTeacher().getTeacher().getName());
        map.put("subjectTime", source.getTime());
        map.put("subjectAddress", source.getAddress());
        map.put("subjectGroups", source.getGroups());
        map.put("paper", content);
        return map;
    }

    /*生成成绩表*/
    @Override
    public Subject createEndTable(HttpServletRequest request, Subject form) throws DocumentException, IOException {
        Subject entity = subjectDAO.findById(form.getId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusList.get(0);
        entity.setNote(form.getNote());
        entity.setScore(form.getScore());
        Map<String, Object> dataMap = createScoreDataMap(entity);
        entity.setScorePdfUrl(createPdf("score", dataMap));
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /*准备生成成绩表pdf所需数据*/
    private Map<String, Object> createScoreDataMap(Subject source) {
        Map<String, Object> map = new HashMap<>();
        map.put("subjectName", source.getName());
        map.put("subjectSelector", source.getSelecter().getName());
        map.put("guideTeacher", source.getGuideTeacher().getTeacher().getName());
        map.put("reviewTeacher", source.getReviewTeacher().getTeacher().getName());
        map.put("score", source.getScore());
        return map;
    }

    /*准备要转换为pdf的html字符串*/
    private String createHtml(String template, Map<String, Object> dataMap) {
        Context context = new Context();
        context.setVariables(dataMap);
        return engine.process(template, context);
    }

    /*生成pdf文件*/
    private String createPdf(String template, Map<String, Object> dataMap) throws IOException, DocumentException {
        String fullName = UUID.randomUUID().toString().replace("-", "") + ".pdf";
        String pdfPath = uploadPath + fullName.toLowerCase();
        String content = createHtml(template, dataMap);
        Charset charset = StandardCharsets.UTF_8;
        FileOutputStream outputStream = new FileOutputStream(pdfPath);
        ByteArrayInputStream contentStream = new ByteArrayInputStream(content.getBytes(charset));
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, contentStream, null, charset);
        document.close();
        return fullName;
    }

    /*优秀论文评定*/
    @Override
    public Subject isGood(HttpServletRequest request, String id, String status) {
        Subject entity = subjectDAO.findById(id);
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusList.get(0);
        entity.setIsGood(Integer.parseInt(status));
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }

    /*设置二次答辩时间(最终步骤)*/
    @Override
    public Subject saveTwoTime(HttpServletRequest request, Subject form) {
        Subject entity = subjectDAO.findById(form.getId());
        List<Status> statusList = entity.getStatus();
        statusList.sort((a, b) -> a.getCreateTime().isAfter(b.getCreateTime()) ? 1 : 0);
        Status nowStatus = statusList.get(0);
        entity.setTwoTime(form.getTwoTime());
        statusList.add(nowStatus.next());
        entity.setStatus(statusDAO.save(statusList));
        return subjectDAO.save(entity);
    }
}

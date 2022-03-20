package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.EmploymentDAO;
import org.example.application.custom.entity.Employment;
import org.example.application.custom.form.EmploymentForm;
import org.example.application.custom.form.OtherForm;
import org.example.application.custom.service.EmploymentService;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class EmploymentServiceImpl implements EmploymentService {
    private final EmploymentDAO employmentDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public EmploymentServiceImpl(EmploymentDAO employmentDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.employmentDAO = employmentDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<Employment> getAll() {
        return employmentDAO.getAll();
    }

    @Override
    public List<Employment> search(String keywords) {
        return employmentDAO.search(keywords);
    }

    @Override
    public Employment save(HttpServletRequest request, EmploymentForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Employment entity = form.toEntity();
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        return employmentDAO.save(entity);
    }

    @Override
    public List<Employment> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return employmentDAO.getBySysUser(user);
    }


    @Override
    public void deleteById(String id) {
        employmentDAO.deleteById(id);
    }

    @Override
    public void checkPass(EmploymentForm form) {
        employmentDAO.updateStatusById(form.getId(),DIC.CHECK_PASS,form.getNote());
    }

    @Override
    public void checkFail(EmploymentForm form) {
        employmentDAO.updateStatusById(form.getId(),DIC.CHECK_FAIL,form.getNote());
    }
}

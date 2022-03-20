package org.example.application.custom.service.impl;

import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.OtherDAO;
import org.example.application.custom.entity.Other;
import org.example.application.custom.form.OtherForm;
import org.example.application.custom.service.MessageService;
import org.example.application.custom.service.OtherService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {
    private final OtherDAO otherDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public OtherServiceImpl(OtherDAO otherDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.otherDAO = otherDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<Other> getAll() {
        return otherDAO.getAll();
    }

    @Override
    public List<Other> search(String keywords) {
        return otherDAO.search(keywords);
    }

    @Override
    public Other save(HttpServletRequest request, OtherForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Other entity = form.toEntity();
        entity.setPublisher(user);
        entity.setStatus(DIC.CHECK_WAIT);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        return otherDAO.save(entity);
    }

    @Override
    public List<Other> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return otherDAO.getBySysUser(user);
    }

    @Override
    public void deleteById(String id) {
        otherDAO.deleteById(id);
    }

    @Override
    public void checkPass(OtherForm form) {
        otherDAO.updateStatusById(form.getId(),DIC.CHECK_PASS,form.getNote());
    }

    @Override
    public void checkFail(OtherForm form) {
        otherDAO.updateStatusById(form.getId(),DIC.CHECK_FAIL,form.getNote());
    }
}

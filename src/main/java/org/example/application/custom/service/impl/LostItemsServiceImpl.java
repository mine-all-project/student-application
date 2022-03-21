package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.LostItemsDAO;
import org.example.application.custom.entity.LostItems;
import org.example.application.custom.form.LostItemsForm;
import org.example.application.custom.service.LostItemsService;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class LostItemsServiceImpl implements LostItemsService {
    private final LostItemsDAO lostItemsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public LostItemsServiceImpl(LostItemsDAO lostItemsDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.lostItemsDAO = lostItemsDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<LostItems> getAll() {
        return lostItemsDAO.getAll();
    }

    @Override
    public List<LostItems> search(String keywords) {
        return lostItemsDAO.search(keywords);
    }

    @Override
    public LostItems save(HttpServletRequest request, LostItemsForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        LostItems entity = form.toEntity();
        checkPublishStatus(entity, request, jwtConfigure, userDAO, isDebug);
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        return lostItemsDAO.save(entity);
    }

    @Override
    public List<LostItems> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return lostItemsDAO.getBySysUser(user);
    }


    @Override
    public void deleteById(String id) {
        lostItemsDAO.deleteById(id);
    }

    @Override
    public void checkPass(LostItemsForm form) {
        lostItemsDAO.updateStatusById(form.getId(), DIC.CHECK_PASS, form.getNote());
    }

    @Override
    public void checkFail(LostItemsForm form) {
        lostItemsDAO.updateStatusById(form.getId(), DIC.CHECK_FAIL, form.getNote());
    }
}

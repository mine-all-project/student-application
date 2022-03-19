package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.DemandDAO;
import org.example.application.custom.entity.Demand;
import org.example.application.custom.form.DemandForm;
import org.example.application.custom.service.DemandService;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件功能实现类
 */
@Slf4j
@Service
public class DemandServiceImpl implements DemandService {
    private final DemandDAO demandDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public DemandServiceImpl(DemandDAO demandDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.demandDAO = demandDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<Demand> getAll() {
        return demandDAO.getAll();
    }

    @Override
    public List<Demand> search(String keywords) {
        return demandDAO.search(keywords);
    }

    @Override
    public Demand save(HttpServletRequest request, DemandForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Demand entity = form.toEntity();
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        return demandDAO.save(entity);
    }

    @Override
    public List<Demand> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return demandDAO.getBySysUser(user);
    }


    @Override
    public void deleteById(String id) {
        demandDAO.deleteById(id);
    }
}

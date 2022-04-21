package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PaperDAO;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;
import org.example.application.custom.service.MessageService;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PaperServiceImpl implements PaperService {
    private final PaperDAO paperDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public PaperServiceImpl(PaperDAO paperDAO, UserDAO userDAO,
                            JwtConfigure jwtConfigure, MessageService messageService) {
        this.paperDAO = paperDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<Paper> getAll() {
        return paperDAO.getAll();
    }

    @Override
    public List<Paper> getListByType(String type) {
        return paperDAO.getListByType(type);
    }

    @Override
    public List<Paper> search(String keywords) {
        return paperDAO.search(keywords);
    }

    @Override
    public Paper save(HttpServletRequest request, PaperForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Paper entity = form.toEntity();
        entity.setCreateTime(LocalDateTime.now());
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        return paperDAO.save(entity);
    }

    @Override
    public void deleteById(String id) {
        paperDAO.deleteById(id);
    }

    @Override
    public void checkPass(PaperForm form) {
        paperDAO.updateStatusById(form.getId(), DIC.CHECK_PASS);
    }

    @Override
    public void checkFail(PaperForm form) {
        paperDAO.updateStatusById(form.getId(), DIC.CHECK_FAIL);
    }

    @Override
    public List<Paper> getMineAll(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return paperDAO.getBySysUser(user);
    }

    @Override
    public List<Paper> getMineListByType(HttpServletRequest request, String type) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return paperDAO.getBySysUserAndType(user,type);
    }
}

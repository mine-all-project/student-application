package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PaperDAO;
import org.example.application.custom.entity.Message;
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
import java.util.Map;

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
    public List<Paper> search(String type, String keywords) {
        return paperDAO.search(type,keywords);
    }

    @Override
    public Paper save(HttpServletRequest request, PaperForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Paper entity = form.toEntity();
        entity.setCreateTime(LocalDateTime.now());
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        if ("news".equals(entity.getType()) || "employment".equals(entity.getType())) {
            entity.setStatus(DIC.CHECK_PASS);
        }
        return paperDAO.save(entity);
    }

    @Override
    public void deleteById(String id) {
        paperDAO.deleteById(id);
    }


    @Override
    public void check(PaperForm form) {
        paperDAO.updateStatusById(form.getId(), form.getStatus(), form.getNote());
    }

    @Override
    public List<Paper> getMineAll(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return paperDAO.getBySysUser(user);
    }

    @Override
    public List<Paper> getMineListByType(HttpServletRequest request, String type) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return paperDAO.getBySysUserAndType(user, type);
    }

    @Override
    public void addComment(HttpServletRequest request, Map<String, Object> comment) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        String id = String.valueOf(comment.getOrDefault("id", ""));
        String content = String.valueOf(comment.getOrDefault("content", ""));
        Paper paper = paperDAO.getById(id);
        List<Message> messages = paper.getMessages();
        Message message = new Message();
        message.setPublisher(user);
        message.setContent(content);
        message = messageService.save(request, message);
        messages.add(message);
        paper.setMessages(messages);
        paperDAO.save(paper);
    }
}

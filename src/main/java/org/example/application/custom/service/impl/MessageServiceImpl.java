package org.example.application.custom.service.impl;

import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.MessageDAO;
import org.example.application.custom.entity.Message;
import org.example.application.custom.form.MessageForm;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public MessageServiceImpl(MessageDAO messageDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public Message save(HttpServletRequest request, MessageForm form) {
        Message entity = form.toEntity();
        return save(request, entity);
    }

    @Override
    public Message save(HttpServletRequest request, Message entity) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        entity.setPublisher(user);
        entity.setStatus(DIC.CHECK_WAIT);
        return messageDAO.save(entity);
    }

    @Override
    public Message save( Message entity) {
        return messageDAO.save(entity);
    }

    @Override
    public List<Message> saveAll(HttpServletRequest request, List<Message> list) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        final List<Message> collect = list.stream().peek(e -> {
            e.setPublisher(user);
            e.setStatus(DIC.CHECK_WAIT);
        }).collect(Collectors.toList());
        return messageDAO.saveAll(collect);
    }
}

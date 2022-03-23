package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.MessageDAO;
import org.example.application.custom.entity.Message;
import org.example.application.custom.service.MessageService;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDao;
    private final PaperService paperService;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public MessageServiceImpl(MessageDAO messageDao, PaperService paperService,
                              UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.messageDao = messageDao;
        this.paperService = paperService;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Message> getMessageByPaperId(String id) {
        return messageDao.getMessageByPaperId(id);
    }

    @Override
    public void addMessage(HttpServletRequest request, Message message) {
        message.setId(UUID.randomUUID().toString());
        message.setUserId(getUserInfo(request, jwtConfigure, userDAO, isDebug).getId());
        messageDao.addMessage(message);
    }
}

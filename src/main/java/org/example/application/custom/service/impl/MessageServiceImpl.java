package org.example.application.custom.service.impl;

import org.example.application.custom.dao.MessageDAO;
import org.example.application.custom.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDAO;

    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

}

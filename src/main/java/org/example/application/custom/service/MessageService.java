package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MessageService extends BaseService {
    List<Message> getMessageByPaperId(String id);

    void addMessage(HttpServletRequest request, Message message);
}

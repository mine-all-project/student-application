package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Message;
import org.example.application.custom.form.MessageForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MessageService extends BaseService {
    Message save(HttpServletRequest request, MessageForm form);

    Message save(HttpServletRequest request, Message entity);

    Message save(Message entity);

    List<Message> saveAll(HttpServletRequest request, List<Message> list);
}

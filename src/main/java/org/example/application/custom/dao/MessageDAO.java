package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.MessageRepository;
import org.example.application.custom.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDAO extends BaseDAO {
    private final MessageRepository messageRepository;

    public MessageDAO(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message entity) {
        return messageRepository.saveAndFlush(entity);
    }

    public List<Message> saveAll(List<Message> collect) {
        return messageRepository.saveAll(collect);
    }
}

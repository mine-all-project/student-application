package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.MessageMapper;
import org.example.application.custom.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MessageDAO extends BaseDAO {
    private final MessageMapper messageMapper;

    public MessageDAO(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }


    public void addStoreCar(Message type) {
        type.setId(UUID.randomUUID().toString());
        messageMapper.insert(type);
    }

    public List<Message> getMessageByPaperId(String id) {
        return messageMapper.selectByPaperId(id);
    }

    public void addMessage(Message message) {
        messageMapper.insertSelective(message);
    }
}

package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.MessageRepository;
import org.example.yaopin.entity.Messages;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO {
    private final MessageRepository messageRepository;

    public MessageDAO(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveData(Messages messages) {
        messageRepository.saveAndFlush(messages);
    }

    public List<Messages> findByToAsIn(String role) {
        return messageRepository.findAllByToAsIn(role);
    }

    public Messages findById(String id) {
        return messageRepository.findById(id).orElse(null);
    }
}

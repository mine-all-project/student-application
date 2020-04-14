package org.example.yangjichang.dao;

import org.example.yangjichang.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Serializable> {
    List<Message> findAllByArea(Integer area);
}

package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Serializable> {
    List<Messages> findAllByToAsIn(String toAs);
}

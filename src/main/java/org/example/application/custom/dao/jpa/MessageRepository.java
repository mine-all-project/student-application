package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统菜单持久层
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}

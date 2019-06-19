package com.example.hzj.dao;

import com.example.hzj.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MessageDao extends JpaRepository<Message, Serializable> {
}

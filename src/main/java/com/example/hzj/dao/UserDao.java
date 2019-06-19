package com.example.hzj.dao;

import com.example.hzj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface UserDao extends JpaRepository<User, Serializable> {
}

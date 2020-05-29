package com.example.demo.dao;

import com.example.demo.entity.MineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MineInfoDao extends JpaRepository<MineInfo, Serializable> {
}

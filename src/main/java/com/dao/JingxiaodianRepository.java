package com.dao;

import com.entity.Jingxiaodian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JingxiaodianRepository extends JpaRepository<Jingxiaodian, String> {
}

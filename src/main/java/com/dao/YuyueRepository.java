package com.dao;

import com.entity.Yuyue;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface YuyueRepository extends JpaRepository<Yuyue, String> {
    List<Yuyue> findAllByType(String type, Sort sort);
}

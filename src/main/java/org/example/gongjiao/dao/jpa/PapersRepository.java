package org.example.gongjiao.dao.jpa;

import org.example.gongjiao.entity.Papers;
import org.example.gongjiao.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PapersRepository extends JpaRepository<Papers, String> {
    List<Papers> findByKeyWords(String keyWords, Sort sort);

}

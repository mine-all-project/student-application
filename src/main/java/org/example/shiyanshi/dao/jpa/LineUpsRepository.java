package org.example.shiyanshi.dao.jpa;

import org.example.shiyanshi.entity.LineUps;
import org.example.shiyanshi.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineUpsRepository extends JpaRepository<LineUps, String> {

    List<LineUps> findAllByUser(Sort sort, SysUser user);

//    List<Purchases> findByStatusNotAndDelFlag(Sort sort, int status, int delFlag);
}

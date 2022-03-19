package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Demand;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {
    List<Demand> findAllByTitleLike(String keywords, Sort sort);

    List<Demand> findAllByPublisher(SysUser publisher, Sort sort);
}

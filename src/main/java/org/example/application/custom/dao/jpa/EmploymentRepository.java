package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Employment;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, String> {
    List<Employment> findAllByTitleLike(String keywords, Sort sort);

    List<Employment> findAllByPublisher(SysUser publisher, Sort sort);
}

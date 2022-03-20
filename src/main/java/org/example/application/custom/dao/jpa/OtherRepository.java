package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Other;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherRepository extends JpaRepository<Other, String> {
    List<Other> findAllByTitleLike(String keywords, Sort sort);

    List<Other> findAllByPublisher(SysUser publisher, Sort sort);

    @Modifying
    @Query("update Other set status = :status,note = :note where id = :id")
    void updateStatusById(@Param("id") String id, @Param("status") int status, @Param("note") String note);
}

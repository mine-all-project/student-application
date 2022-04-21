package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Paper;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findAllByTitleLike(String keywords, Sort sort);

    List<Paper> findAllByPublisher(SysUser publisher, Sort sort);

    @Modifying
    @Query("update Paper set status = :status where id = :id")
    void updateStatusById(@Param("id") String id, @Param("status") int status);

    List<Paper> findAllByType(String type, Sort sort);

    List<Paper> getByPublisherAndType(SysUser user, String type, Sort sort);
}

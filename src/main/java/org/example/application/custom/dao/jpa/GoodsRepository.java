package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Goods;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findAllByNameLike(String keywords, Sort sort);

    List<Goods> findAllByPublisher(SysUser publisher, Sort sort);

    @Modifying
    @Query("update Goods set status = :status,note = :note where id = :id")
    void updateStatusById(@Param("id") String id, @Param("status") int status, @Param("note") String note);
}

package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.CarPooling;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPoolingRepository extends JpaRepository<CarPooling, String> {
    List<CarPooling> findAllByTitleLike(String keywords, Sort sort);

    List<CarPooling> findAllByPublisher(SysUser publisher, Sort sort);

    List<CarPooling> findAllByFromAddress(String fromAddress, Sort sort);

    List<CarPooling> findAllByTargetAddress(String targetAddress, Sort sort);

    List<CarPooling> findAllByFromAddressAndTargetAddress(String fromAddress, String targetAddress, Sort sort);

    @Modifying
    @Query("update CarPooling set status = :status,note = :note where id = :id")
    void updateStatusById(@Param("id") String id, @Param("status") int status, @Param("note") String note);
}

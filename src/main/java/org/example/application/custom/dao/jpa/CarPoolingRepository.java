package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.CarPooling;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPoolingRepository extends JpaRepository<CarPooling, String> {
    List<CarPooling> findAllByTitleLike(String keywords, Sort sort);

    List<CarPooling> findAllByPublisher(SysUser publisher, Sort sort);

    List<CarPooling> findAllByFromAddress(String fromAddress, Sort sort);

    List<CarPooling> findAllByTargetAddress(String targetAddress, Sort sort);

    List<CarPooling> findAllByFromAddressAndTargetAddress(String fromAddress, String targetAddress, Sort sort);
}

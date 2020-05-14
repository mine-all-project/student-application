package org.example.shiyanshi.dao.jpa;

import org.example.shiyanshi.entity.Purchases;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, String> {

    List<Purchases> findByStatusAndDelFlag(Sort sort, int status, int delFlag);

    List<Purchases> findByStatusNotAndDelFlag(Sort sort, int status, int delFlag);
}

package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, String> {

//    List<Purchases> findByNumberLike(String number);
}

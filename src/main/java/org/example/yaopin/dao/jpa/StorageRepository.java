package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Purchases, String> {

    List<Purchases> findByNumberLike(String number);
}

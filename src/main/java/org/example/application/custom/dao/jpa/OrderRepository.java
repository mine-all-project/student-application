package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
//    List<Order> findByKeywordContains(String keyword, Sort sort);

    List<Order> findByCreateTimeBetween(LocalDateTime beginTime, LocalDateTime endTime, Sort sort);

    @Modifying
    @Query("update Order set status = :status where id = :id")
    void updateStatusById(String id, int status);
}

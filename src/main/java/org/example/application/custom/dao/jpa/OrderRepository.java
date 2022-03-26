package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByKeywordLike(String keyword, Sort desByCreateTime);
}

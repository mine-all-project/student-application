package org.example.shiyanshi.dao.jpa;

import org.example.shiyanshi.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, String> {

}

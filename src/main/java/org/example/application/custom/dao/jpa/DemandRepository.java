package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {
}

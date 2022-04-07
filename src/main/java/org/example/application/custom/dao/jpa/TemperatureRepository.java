package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, String> {
}

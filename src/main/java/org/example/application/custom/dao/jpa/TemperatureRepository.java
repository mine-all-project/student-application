package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Temperature;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, String> {
    List<Temperature> findByUser(SysUser user, Sort sort);
}

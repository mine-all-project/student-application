package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.StoreCar;
import org.example.application.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreCarRepository extends JpaRepository<StoreCar, String> {
     Optional<StoreCar> findByUser(SysUser user) ;

}

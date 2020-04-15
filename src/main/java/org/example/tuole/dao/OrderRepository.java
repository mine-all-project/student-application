package org.example.tuole.dao;

import org.example.tuole.entity.Orders;
import org.example.tuole.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findAllBySysUserAndOrderTime(SysUser sysUser, LocalDate now);
}

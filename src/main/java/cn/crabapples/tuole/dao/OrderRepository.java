package cn.crabapples.tuole.dao;

import cn.crabapples.tuole.entity.Orders;
import cn.crabapples.tuole.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO 订单信息持久层
 *
 *
 * 2019/7/4 1422:51
 *
 *
 * pc-name 29404
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findAllBySysUserAndOrderTime(SysUser sysUser, LocalDate now);
}

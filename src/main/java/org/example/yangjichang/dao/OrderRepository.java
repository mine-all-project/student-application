package org.example.yangjichang.dao;

import org.example.yangjichang.entity.Orders;
import org.example.yangjichang.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO 订单信息持久层
 *
 * @author Mr.He
 * 2019/7/4 1422:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findAllBySysUserAndOrderTime(SysUser sysUser, LocalDate now);
}

package cn.crabapples.tuole.dao;

import cn.crabapples.tuole.entity.Orders;
import cn.crabapples.tuole.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

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

    Optional<Orders> findAllBySysUser(SysUser sysUser);
    Optional<Orders> findAllBySysUserNotAndOrderTimeNot(SysUser sysUser, LocalDate orderTime);

    Optional<Orders> findAllByOrderTimeNot(LocalDate now);

    Optional<Orders> findAllBySysUserAndOrderTime(SysUser sysUser, LocalDate now);
}

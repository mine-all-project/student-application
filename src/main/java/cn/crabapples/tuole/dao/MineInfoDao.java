package cn.crabapples.tuole.dao;

import cn.crabapples.tuole.entity.MineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MineInfoDao extends JpaRepository<MineInfo, Serializable> {
}

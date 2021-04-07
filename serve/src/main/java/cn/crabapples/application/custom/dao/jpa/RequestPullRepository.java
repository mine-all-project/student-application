package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.RequestPull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestPullRepository extends JpaRepository<RequestPull, String> {


}

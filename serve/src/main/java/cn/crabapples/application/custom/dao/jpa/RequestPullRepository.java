package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.RequestPull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestPullRepository extends JpaRepository<RequestPull, String> {

    List<RequestPull> findAllByStatusAndDelFlag(int status, int delFla);

    RequestPull findByIdAndDelFlag(String id, int delFla);
}

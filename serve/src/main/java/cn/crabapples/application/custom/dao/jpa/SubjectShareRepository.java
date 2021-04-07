package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubjectShareRepository extends JpaRepository<SubjectShare, String> {
    Optional<SubjectShare> findBySubjectAndStatus(Subject subject, int status);

    List<SubjectShare> findByStatusAndShareByNot(int status, SysUser shareBy);

    List<SubjectShare> findByStatus(int status);
}

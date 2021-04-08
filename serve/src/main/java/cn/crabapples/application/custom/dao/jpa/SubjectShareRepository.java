package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 1:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Repository
public interface SubjectShareRepository extends JpaRepository<SubjectShare, String> {
    Optional<SubjectShare> findBySubjectAndStatus(Subject subject, int status);

    List<SubjectShare> findByStatusAndShareByNot(int status, SysUser shareBy);

    List<SubjectShare> findByStatus(int status);
}

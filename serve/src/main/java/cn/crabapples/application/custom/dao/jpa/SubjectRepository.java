package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findByIdAndDelFlag(String id, int delFlag);

    Subject findByIdAndStatusAndDelFlag(String id, int status, int delFlag);

    List<Subject> findByCreateByAndDelFlag(Sort sort, SysUser createById, int delFlag);

    List<Subject> findByCreateByNotAndDelFlagAndIsShare(Sort sort, SysUser createById, int delFlag, int isShare);

}

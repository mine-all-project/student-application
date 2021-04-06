package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findByIdAndDelFlag(String id, int delFlag);

}

package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Subject$StepRepository extends JpaRepository<Subject.Step, String> {
    Subject.Step findByIdAndDelFlagAndStatus(String id, int delFlag, int status);

    Subject.Step findByIdAndDelFlag(String id, int delFlag);
}

package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.SubjectStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 科研项目阶段数据库交互
 */
@Repository
public interface SubjectStepRepository extends JpaRepository<SubjectStep, String> {
    SubjectStep findByIdAndDelFlagAndStatus(String id, int delFlag, int status);

    SubjectStep findByIdAndDelFlag(String id, int delFlag);
}

package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.SubjectStepResultInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 科研项目阶段成果数据库交互
 */
@Repository
public interface SubjectStepResultInfoRepository extends JpaRepository<SubjectStepResultInfo, String> {
}

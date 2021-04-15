package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.entity.Subject;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 评论数据库交互
 */
@Repository
public interface DiscussRepository extends JpaRepository<Discuss, String> {
    Discuss findByIdAndDelFlag(String parentId, int delFlag);

    List<Discuss> findBySubjectNotNullAndDelFlag(Sort desByCreateTime, int delFlag);

    List<Discuss> findBySubjectAndDelFlag(Sort desByCreateTime, Subject subject, int delFlag);
}

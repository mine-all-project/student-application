package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.SubjectStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface SubjectStepRepository extends JpaRepository<SubjectStep, String> {
    SubjectStep findByIdAndDelFlagAndStatus(String id, int delFlag, int status);

    SubjectStep findByIdAndDelFlag(String id, int delFlag);
}

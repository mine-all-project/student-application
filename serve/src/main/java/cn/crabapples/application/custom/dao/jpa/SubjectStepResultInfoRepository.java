package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.SubjectStepResultInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:50
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Repository
public interface SubjectStepResultInfoRepository extends JpaRepository<SubjectStepResultInfo, String> {
}

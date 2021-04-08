package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.RequestPull;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface RequestPullRepository extends JpaRepository<RequestPull, String> {

    List<RequestPull> findAllByStatusAndDelFlag(int status, int delFla);

    RequestPull findByIdAndDelFlag(String id, int delFla);

    RequestPull findBySysUserAndSubjectAndDelFlag(SysUser sysUser, Subject subject, int delFlag);

}

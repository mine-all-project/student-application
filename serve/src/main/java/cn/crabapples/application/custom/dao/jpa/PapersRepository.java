package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Papers1;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 22:44
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Repository
public interface PapersRepository extends JpaRepository<Papers1, String> {
    List<Papers1> findAllByDelFlag(Sort sort, int delFla);
    Papers1 findByIdAndDelFlag(String id, int delFla);
}

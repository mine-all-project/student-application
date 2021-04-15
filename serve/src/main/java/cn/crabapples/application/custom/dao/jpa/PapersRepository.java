package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Papers1;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章数据库交互
 */
@Repository
public interface PapersRepository extends JpaRepository<Papers1, String> {
    List<Papers1> findAllByDelFlag(Sort sort, int delFla);
    Papers1 findByIdAndDelFlag(String id, int delFla);
}

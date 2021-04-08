package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Tags;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface TagsRepository extends JpaRepository<Tags, String> {
    List<Tags> findAllByDelFlagNot(Sort sort, int delFlag);

    Tags findByIdAndDelFlag(String id, int delFlag);

    List<Tags> findByIdInAndDelFlag(List<String> ids, int delFlag);
}

package cn.crabapples.system.dao.jpa;

import cn.crabapples.system.entity.Subject;
import cn.crabapples.system.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagsRepository extends JpaRepository<Tags, String> {
}

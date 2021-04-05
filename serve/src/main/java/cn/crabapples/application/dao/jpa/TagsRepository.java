package cn.crabapples.application.dao.jpa;

import cn.crabapples.application.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagsRepository extends JpaRepository<Tags, String> {
}

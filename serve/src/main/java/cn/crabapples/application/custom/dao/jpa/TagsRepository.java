package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagsRepository extends JpaRepository<Tags, String> {
}

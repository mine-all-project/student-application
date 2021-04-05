package cn.crabapples.application.dao.jpa;

import cn.crabapples.application.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
}

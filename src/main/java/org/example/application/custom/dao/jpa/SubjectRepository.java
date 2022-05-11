package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.Subject;
import org.example.application.system.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByNameContains(String name);

    Subject findBySelecter(SystemUser user);
}

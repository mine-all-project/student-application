package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.SubjectRepository;
import org.example.application.custom.entity.Subject;
import org.example.application.system.entity.SystemUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;

    public SubjectDAO(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject save(Subject entity) {
        return subjectRepository.saveAndFlush(entity);
    }

    public List<Subject> getList() {
        return subjectRepository.findAll(desByCreateTime);
    }

    public List<Subject> getListByName(String keyword) {
        return subjectRepository.findByNameContains(keyword);
    }

    public Subject findById(String id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject findBySelecter(SystemUser user) {
        return subjectRepository.findBySelecter(user);
    }

    public void deleteById(String id) {
        subjectRepository.deleteById(id);
    }


}

package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.EmploymentRepository;
import org.example.application.custom.entity.Employment;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmploymentDAO extends BaseDAO {
    private final EmploymentRepository employmentRepository;

    public EmploymentDAO(EmploymentRepository employmentRepository) {
        this.employmentRepository = employmentRepository;
    }

    public List<Employment> getAll() {
        return employmentRepository.findAll(desByCreateTime);
    }

    public List<Employment> search(String keywords) {
        return employmentRepository.findAllByTitleLike(keywords, desByCreateTime);
    }

    public Employment save(Employment entity) {
        return employmentRepository.saveAndFlush(entity);
    }

    public List<Employment> getBySysUser(SysUser user) {
        return employmentRepository.findAllByPublisher(user, desByCreateTime);
    }

    public void deleteById(String id) {
        employmentRepository.deleteById(id);
    }

    @Transactional
    public void updateStatusById(String id, int status,String note) {
        employmentRepository.updateStatusById(id, status, note);
    }
}

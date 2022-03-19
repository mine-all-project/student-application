package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.DemandRepository;
import org.example.application.custom.entity.Demand;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemandDAO extends BaseDAO {
    private final DemandRepository demandRepository;

    public DemandDAO(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    public List<Demand> getAll() {
        return demandRepository.findAll(desByCreateTime);
    }

    public List<Demand> search(String keywords) {
        return demandRepository.findAllByTitleLike(keywords,desByCreateTime);
    }

    public Demand save(Demand entity) {
        return demandRepository.saveAndFlush(entity);
    }

    public List<Demand> getBySysUser(SysUser user) {
        return demandRepository.findAllByPublisher(user,desByCreateTime);
    }

    public void deleteById(String id) {
        demandRepository.deleteById(id);
    }
}

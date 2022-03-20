package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.OtherRepository;
import org.example.application.custom.entity.Other;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtherDAO extends BaseDAO {
    private final OtherRepository otherRepository;

    public OtherDAO(OtherRepository otherRepository) {
        this.otherRepository = otherRepository;
    }

    public List<Other> getAll() {
        return otherRepository.findAll(desByCreateTime);
    }

    public Other save(Other entity) {
        return otherRepository.saveAndFlush(entity);
    }

    public List<Other> search(String keywords) {
        return otherRepository.findAllByTitleLike(keywords,desByCreateTime);
    }

    public List<Other> getBySysUser(SysUser user) {
        return otherRepository.findAllByPublisher(user,desByCreateTime);
    }

    public void deleteById(String id) {
        otherRepository.deleteById(id);
    }
}

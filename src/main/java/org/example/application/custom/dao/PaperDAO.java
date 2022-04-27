package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.PaperRepository;
import org.example.application.custom.entity.Paper;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PaperDAO extends BaseDAO {
    private final PaperRepository paperRepository;

    public PaperDAO(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public List<Paper> getAll() {
        return paperRepository.findAll(desByCreateTime);
    }

    public List<Paper> search(String type, String keywords) {
        return paperRepository.findAllByTypeAndContentContains(type,keywords,desByCreateTime);
    }

    public Paper save(Paper entity) {
        return paperRepository.saveAndFlush(entity);
    }

    public List<Paper> getBySysUser(SysUser user) {
        return paperRepository.findAllByPublisher(user,desByCreateTime);
    }

    public void deleteById(String id) {
        paperRepository.deleteById(id);
    }


    @Transactional
    public void updateStatusById(String id, int status, String note) {
        paperRepository.updateStatusById(id, status,note);
    }

    public List<Paper> getListByType(String type) {
        return paperRepository.findAllByType(type,desByCreateTime);
    }

    public List<Paper> getBySysUserAndType(SysUser user, String type) {
        return paperRepository.getByPublisherAndType(user,type,desByCreateTime);
    }

    public Paper getById(String id) {
        return paperRepository.findById(id).orElse(null);
    }
}

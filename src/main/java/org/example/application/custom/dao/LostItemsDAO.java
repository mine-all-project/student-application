package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.LostItemsRepository;
import org.example.application.custom.entity.LostItems;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LostItemsDAO extends BaseDAO {
    private final LostItemsRepository lostItemsRepository;

    public LostItemsDAO(LostItemsRepository lostItemsRepository) {
        this.lostItemsRepository = lostItemsRepository;
    }

    public List<LostItems> getAll() {
        return lostItemsRepository.findAll(desByCreateTime);
    }

    public List<LostItems> search(String keywords) {
        return lostItemsRepository.findAllByTitleLike(keywords,desByCreateTime);
    }

    public LostItems save(LostItems entity) {
        return lostItemsRepository.saveAndFlush(entity);
    }

    public List<LostItems> getBySysUser(SysUser user) {
        return lostItemsRepository.findAllByPublisher(user,desByCreateTime);
    }

    public void deleteById(String id) {
        lostItemsRepository.deleteById(id);
    }


    @Transactional
    public void updateStatusById(String id, int status,String note) {
        lostItemsRepository.updateStatusById(id, status, note);
    }
}

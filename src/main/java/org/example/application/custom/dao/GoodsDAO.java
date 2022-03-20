package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.GoodsRepository;
import org.example.application.custom.entity.Goods;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GoodsDAO extends BaseDAO {
    private final GoodsRepository goodsRepository;

    public GoodsDAO(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAll() {
        return goodsRepository.findAll(desByCreateTime);
    }

    public Goods save(Goods entity) {
        return goodsRepository.saveAndFlush(entity);
    }

    public List<Goods> search(String keywords) {
        return goodsRepository.findAllByNameLike(keywords, desByCreateTime);
    }

    public List<Goods> getBySysUser(SysUser user) {
        return goodsRepository.findAllByPublisher(user, desByCreateTime);
    }

    public void deleteById(String id) {
        goodsRepository.deleteById(id);
    }

    @Transactional
    public void updateStatusById(String id, int status,String note) {
        goodsRepository.updateStatusById(id, status, note);
    }
}

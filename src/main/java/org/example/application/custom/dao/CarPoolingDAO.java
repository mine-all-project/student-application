package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.CarPoolingRepository;
import org.example.application.custom.entity.CarPooling;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CarPoolingDAO extends BaseDAO {
    private final CarPoolingRepository carPoolingRepository;

    public CarPoolingDAO(CarPoolingRepository carPoolingRepository) {
        this.carPoolingRepository = carPoolingRepository;
    }

    public List<CarPooling> getAll() {
        return carPoolingRepository.findAll(desByCreateTime);
    }

    public List<CarPooling> search(String keywords) {
        return carPoolingRepository.findAllByTitleLike(keywords, desByCreateTime);
    }

    public CarPooling save(CarPooling entity) {
        return carPoolingRepository.saveAndFlush(entity);
    }

    public List<CarPooling> getBySysUser(SysUser user) {
        return carPoolingRepository.findAllByPublisher(user, desByCreateTime);
    }

    public void deleteById(String id) {
        carPoolingRepository.deleteById(id);
    }

    @Transactional
    public void updateStatusById(String id, int status, String note) {
        carPoolingRepository.updateStatusById(id, status, note);
    }
}

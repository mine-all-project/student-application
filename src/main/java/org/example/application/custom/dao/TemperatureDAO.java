package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.TemperatureRepository;
import org.example.application.custom.entity.Temperature;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureDAO extends BaseDAO {
    private final TemperatureRepository temperatureRepository;

    public TemperatureDAO(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public Temperature saveTemperature(Temperature entity) {
        return temperatureRepository.saveAndFlush(entity);
    }


    public List<Temperature> getAll() {
        return temperatureRepository.findAll(DES_CREATE_TIME);
    }

    public List<Temperature> findByUser(SysUser user) {
        return temperatureRepository.findByUser(user,DES_CREATE_TIME);
    }
}

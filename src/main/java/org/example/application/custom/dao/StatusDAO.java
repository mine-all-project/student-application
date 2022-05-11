package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.StatusRepository;
import org.example.application.custom.entity.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusDAO extends BaseDAO {
    private final StatusRepository statusRepository;

    public StatusDAO(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status save(Status entity) {
        return statusRepository.saveAndFlush(entity);
    }

    public List<Status> save(List<Status> list) {
        return statusRepository.saveAll(list);
    }

    public Status findById(String id) {
        return statusRepository.findById(id).orElse(new Status());
    }
}

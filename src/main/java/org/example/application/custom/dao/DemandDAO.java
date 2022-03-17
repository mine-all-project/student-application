package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.DemandRepository;
import org.springframework.stereotype.Component;

@Component
public class DemandDAO extends BaseDAO {
    private final DemandRepository demandRepository;

    public DemandDAO(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

}

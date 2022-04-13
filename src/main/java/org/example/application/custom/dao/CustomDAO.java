package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.CustomRepository;
import org.example.application.custom.entity.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomDAO extends BaseDAO {
    @Autowired
    private CustomRepository customRepository;

    public Custom get() {
        return customRepository.get().orElse(new Custom());
    }

    public void save(Custom custom) {
        customRepository.save(custom);
    }
}

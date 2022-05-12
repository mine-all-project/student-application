package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.GuideRepository;
import org.example.application.custom.entity.GuideTeacher;
import org.springframework.stereotype.Component;

@Component
public class GuideDAO extends BaseDAO {
    private final GuideRepository guideRepository;

    public GuideDAO(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public GuideTeacher save(GuideTeacher entity) {
        return guideRepository.saveAndFlush(entity);
    }
}

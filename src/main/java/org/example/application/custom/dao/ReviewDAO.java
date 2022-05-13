package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.GuideRepository;
import org.example.application.custom.dao.jpa.ReviewRepository;
import org.example.application.custom.entity.GuideTeacher;
import org.example.application.custom.entity.ReviewTeacher;
import org.springframework.stereotype.Component;

@Component
public class ReviewDAO extends BaseDAO {
    private final ReviewRepository reviewRepository;

    public ReviewDAO(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewTeacher save(ReviewTeacher entity) {
        return reviewRepository.saveAndFlush(entity);
    }
}

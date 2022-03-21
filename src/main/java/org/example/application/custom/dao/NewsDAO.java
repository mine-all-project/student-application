package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.NewsRepository;
import org.example.application.custom.entity.News;
import org.example.application.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NewsDAO extends BaseDAO {
    private final NewsRepository newsRepository;

    public NewsDAO(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAll() {
        return newsRepository.findAll(desByCreateTime);
    }

    public News save(News entity) {
        return newsRepository.saveAndFlush(entity);
    }

    public List<News> search(String keywords) {
        return newsRepository.findAllByTitleLike(keywords, desByCreateTime);
    }

    public List<News> getBySysUser(SysUser user) {
        return newsRepository.findAllByPublisher(user, desByCreateTime);
    }

    public void deleteById(String id) {
        newsRepository.deleteById(id);
    }

    @Transactional
    public void updateStatusById(String id, int status, String note) {
        newsRepository.updateStatusById(id, status, note);
    }
}

package org.example.application.custom.service.impl;

import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.NewsDAO;
import org.example.application.custom.entity.News;
import org.example.application.custom.form.NewsForm;
import org.example.application.custom.service.MessageService;
import org.example.application.custom.service.NewsService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsDAO newsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public NewsServiceImpl(NewsDAO newsDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.newsDAO = newsDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<News> getAll() {
        return newsDAO.getAll();
    }

    @Override
    public List<News> search(String keywords) {
        return newsDAO.search(keywords);
    }

    @Override
    public News save(HttpServletRequest request, NewsForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        News entity = form.toEntity();
        checkPublishStatus(entity, request, jwtConfigure, userDAO, isDebug);
        entity.setPublisher(user);
        entity.setStatus(DIC.CHECK_WAIT);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        return newsDAO.save(entity);
    }

    @Override
    public List<News> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return newsDAO.getBySysUser(user);
    }

    @Override
    public void deleteById(String id) {
        newsDAO.deleteById(id);
    }

    @Override
    public void checkPass(NewsForm form) {
        newsDAO.updateStatusById(form.getId(), DIC.CHECK_PASS, form.getNote());
    }

    @Override
    public void checkFail(NewsForm form) {
        newsDAO.updateStatusById(form.getId(), DIC.CHECK_FAIL, form.getNote());
    }
}

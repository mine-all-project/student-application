package org.example.application.custom.service.impl;

import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.GoodsDAO;
import org.example.application.custom.entity.Goods;
import org.example.application.custom.form.GoodsForm;
import org.example.application.custom.service.GoodsService;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDAO goodsDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public GoodsServiceImpl(GoodsDAO goodsDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.goodsDAO = goodsDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<Goods> getAll() {
        return goodsDAO.getAll();
    }

    @Override
    public List<Goods> search(String keywords) {
        return goodsDAO.search(keywords);
    }

    @Override
    public Goods save(HttpServletRequest request, GoodsForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Goods entity = form.toEntity();
        entity.setPublisher(user);
        entity.setStatus(DIC.CHECK_WAIT);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        return goodsDAO.save(entity);
    }

    @Override
    public List<Goods> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return goodsDAO.getBySysUser(user);
    }

    @Override
    public void deleteById(String id) {
        goodsDAO.deleteById(id);
    }
}

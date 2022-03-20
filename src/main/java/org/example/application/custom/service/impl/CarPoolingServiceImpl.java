package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.DIC;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.CarPoolingDAO;
import org.example.application.custom.entity.CarPooling;
import org.example.application.custom.form.CarPoolingForm;
import org.example.application.custom.service.CarPoolingService;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class CarPoolingServiceImpl implements CarPoolingService {
    private final CarPoolingDAO carPoolingDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final MessageService messageService;

    public CarPoolingServiceImpl(CarPoolingDAO carPoolingDAO, UserDAO userDAO, JwtConfigure jwtConfigure, MessageService messageService) {
        this.carPoolingDAO = carPoolingDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.messageService = messageService;
    }

    @Override
    public List<CarPooling> getAll() {
        return carPoolingDAO.getAll();
    }

    @Override
    public List<CarPooling> search(String keywords) {
        return carPoolingDAO.search(keywords);
    }

    @Override
    public CarPooling save(HttpServletRequest request, CarPoolingForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        CarPooling entity = form.toEntity();
        entity.setPublisher(user);
        entity.setMessages(messageService.saveAll(request, entity.getMessages()));
        entity.setStatus(DIC.CHECK_WAIT);
        return carPoolingDAO.save(entity);
    }

    @Override
    public List<CarPooling> getListByMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return carPoolingDAO.getBySysUser(user);
    }


    @Override
    public void deleteById(String id) {
        carPoolingDAO.deleteById(id);
    }

    @Override
    public void checkPass(CarPoolingForm form) {
        carPoolingDAO.updateStatusById(form.getId(),DIC.CHECK_PASS,form.getNote());
    }

    @Override
    public void checkFail(CarPoolingForm form) {
        carPoolingDAO.updateStatusById(form.getId(),DIC.CHECK_FAIL,form.getNote());
    }
}

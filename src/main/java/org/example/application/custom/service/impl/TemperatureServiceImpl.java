package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.TemperatureDAO;
import org.example.application.custom.entity.Temperature;
import org.example.application.custom.service.TemperatureService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import org.example.application.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class TemperatureServiceImpl implements TemperatureService {
    private final TemperatureDAO temperatureDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final FileInfoService fileInfoService;

    public TemperatureServiceImpl(TemperatureDAO temperatureDAO, UserDAO userDAO, JwtConfigure jwtConfigure, FileInfoService fileInfoService) {
        this.temperatureDAO = temperatureDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.fileInfoService = fileInfoService;
    }

    @Override
    public List<Temperature> getAll() {
        return temperatureDAO.getAll();
    }

    @Override
    public List<Temperature> getMine(HttpServletRequest request) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        return temperatureDAO.findByUser(user);
    }

    @Override
    public Temperature saveTemperature(HttpServletRequest request, Temperature form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        form.setUser(user);
        return temperatureDAO.saveTemperature(form);
    }

}

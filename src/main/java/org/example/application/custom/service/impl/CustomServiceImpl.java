package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.CustomDAO;
import org.example.application.custom.entity.Custom;
import org.example.application.custom.service.CustomService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomServiceImpl implements CustomService {
    private final CustomDAO customDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final FileInfoService fileInfoService;

    public CustomServiceImpl(CustomDAO customDAO, UserDAO userDAO, JwtConfigure jwtConfigure, FileInfoService fileInfoService) {
        this.customDAO = customDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.fileInfoService = fileInfoService;
    }

    private Custom get() {
        return customDAO.get();
    }

    @Override
    public List<FileInfo> getBanner() {
        Custom custom = get();
        return custom.getBanner();
    }

    @Override
    public void saveBanner(List<FileInfo> banners) {
        Custom custom = get();
        custom.setBanner(banners);
        customDAO.save(custom);
    }

    @Override
    public String getBaseNotice() {
        Custom custom = get();
        return custom.getBaseNotice();
    }

    @Override
    public void saveBaseNotice(String content) {
        Custom custom = get();
        custom.setBaseNotice(content);
        customDAO.save(custom);
    }

    @Override
    public String getTemperatureNotice() {
        Custom custom = get();
        return custom.getTemperatureNotice();
    }

    @Override
    public void saveTemperatureNotice(String content) {
        Custom custom = get();
        custom.setTemperatureNotice(content);
        customDAO.save(custom);
    }
}

package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PaperDAO;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaperServiceImpl implements PaperService {
    private final PaperDAO paperDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final FileInfoService fileInfoService;

    public PaperServiceImpl(PaperDAO paperDAO, UserDAO userDAO, JwtConfigure jwtConfigure, FileInfoService fileInfoService) {
        this.paperDAO = paperDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.fileInfoService = fileInfoService;
    }

    @Override
    public List<Paper> getAll() {
        return paperDAO.getAll();
    }


    @Override
    public Paper savePaper(PaperForm form) {
        Paper entity = form.toEntity();
        FileInfo fileInfo = fileInfoService.saveFileInfo(entity.getImage());
        entity.setImage(fileInfo);
        return paperDAO.savePaper(entity);
    }

    @Override
    public Paper findPaperById(String id) {
        return paperDAO.findPaperById(id);
    }

    @Override
    public void deletePaperById(String id) {
        paperDAO.deletePaperById(id);
    }
}

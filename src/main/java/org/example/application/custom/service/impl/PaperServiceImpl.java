package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PaperDAO;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.service.PaperService;
import org.example.application.system.dao.UserDAO;
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

    public PaperServiceImpl(PaperDAO paperDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.paperDAO = paperDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Paper> getAll() {
        return paperDAO.getAll();
    }

    @Override
    public List<Paper> getAllByTypeId(String typeId) {
        return paperDAO.getAllByTypeId(typeId);
    }

    @Override
    public void addPaper(Paper type) {
        paperDAO.addPaper(type);
    }

    @Override
    public void updatePaper(Paper type) {
        paperDAO.updatePaper(type);
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

package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PaperTypeDAO;
import org.example.application.custom.entity.PaperType;
import org.example.application.custom.service.PaperTypeService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaperTypeServiceImpl implements PaperTypeService {
    private final PaperTypeDAO paperTypeDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public PaperTypeServiceImpl(PaperTypeDAO paperTypeDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.paperTypeDAO = paperTypeDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<PaperType> getAllTypes() {
        return paperTypeDAO.getAllTypes();
    }

    @Override
    public void addPaperType(PaperType type) {
        paperTypeDAO.addPaperType(type);
    }

    @Override
    public void updatePaperType(PaperType type) {
        paperTypeDAO.updatePaperType(type);
    }

    @Override
    public PaperType findPaperTypeById(String id) {
        return paperTypeDAO.findPaperTypeById(id);
    }

    @Override
    public void deletePaperTypeById(String id) {
        paperTypeDAO.deletePaperTypeById(id);
    }
}

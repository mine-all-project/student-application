package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Paper;

import java.util.List;

public interface PaperService extends BaseService {
    List<Paper> getAll();

    void addPaper(Paper type);

    Paper findPaperById(String id);

    void deletePaperById(String id);

    void updatePaper(Paper type);

    List<Paper> getAllByTypeId(String typeId);
}

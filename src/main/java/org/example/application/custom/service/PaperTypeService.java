package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.PaperType;

import java.util.List;

public interface PaperTypeService extends BaseService {
    List<PaperType> getAllTypes();

    void addPaperType(PaperType type);

    PaperType findPaperTypeById(String id);

    void deletePaperTypeById(String id);

    void updatePaperType(PaperType type);
}

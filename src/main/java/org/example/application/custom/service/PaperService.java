package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService extends BaseService {
    List<Map> getAll();

    void addPaper(Paper type);

    Map findPaperById(String id);

    void deletePaperById(String id);

    void updatePaper(Paper type);

    List<Map> getAllByTypeId(String typeId);

    List<Map> selectByIds(List<String> ids);
}

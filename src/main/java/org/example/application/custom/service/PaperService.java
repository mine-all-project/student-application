package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;

import java.util.List;

public interface PaperService  extends BaseService {
    List<Paper> getAll();

    Paper savePaper(PaperForm form);

    Paper findPaperById(String id);

    void deletePaperById(String id);

}

package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaperService  extends BaseService {
    List<Paper> getAll();

    Paper savePaper(HttpServletRequest request, PaperForm form);

    Paper findPaperById(String id);

    void deletePaperById(HttpServletRequest request, String id);

    List<Paper> search(String keyword);
}

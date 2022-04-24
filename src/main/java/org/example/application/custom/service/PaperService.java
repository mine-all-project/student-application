package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaperService extends BaseService {
    List<Paper> getAll();
    List<Paper> getListByType(String type);

    List<Paper> search(String keywords);

    Paper save(HttpServletRequest request, PaperForm form);

    void deleteById(String id);

    List<Paper> getMineAll(HttpServletRequest request);

    List<Paper> getMineListByType(HttpServletRequest request, String type);

    void check(PaperForm form);
}

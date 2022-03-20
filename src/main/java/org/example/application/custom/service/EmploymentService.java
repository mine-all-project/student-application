package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Employment;
import org.example.application.custom.form.EmploymentForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EmploymentService extends BaseService {
    List<Employment> getAll();

    List<Employment> search(String keywords);

    Employment save(HttpServletRequest request, EmploymentForm form);

    List<Employment> getListByMine(HttpServletRequest request);

    void deleteById(String id);
}

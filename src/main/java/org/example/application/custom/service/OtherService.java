package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Other;
import org.example.application.custom.form.OtherForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OtherService extends BaseService {
    List<Other> getAll();

    Other save(HttpServletRequest request,OtherForm form);

    List<Other> search(String keywords);

    List<Other> getListByMine(HttpServletRequest request);

    void deleteById(String id);
}

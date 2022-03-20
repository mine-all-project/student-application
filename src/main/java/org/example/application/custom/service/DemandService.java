package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Demand;
import org.example.application.custom.form.DemandForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DemandService extends BaseService {
    List<Demand> getAll();

    List<Demand> search(String keywords);

    Demand save(HttpServletRequest request, DemandForm form);

    List<Demand> getListByMine(HttpServletRequest request);

    void deleteById(String id);

    void checkPass(DemandForm form);

    void checkFail(DemandForm form);
}

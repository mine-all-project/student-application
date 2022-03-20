package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.CarPooling;
import org.example.application.custom.form.CarPoolingForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CarPoolingService extends BaseService {
    List<CarPooling> getAll();

    List<CarPooling> search(String keywords);

    CarPooling save(HttpServletRequest request, CarPoolingForm form);

    List<CarPooling> getListByMine(HttpServletRequest request);

    void deleteById(String id);
}

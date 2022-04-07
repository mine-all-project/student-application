package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Temperature;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TemperatureService extends BaseService {
    List<Temperature> getAll();

    Temperature saveTemperature(HttpServletRequest request, Temperature form);

}

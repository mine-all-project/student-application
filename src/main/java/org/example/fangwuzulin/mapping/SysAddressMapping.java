package org.example.fangwuzulin.mapping;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysAddressMapping {
    List<Map<String,Object>> findAllProvinces();

    List<Map<String, Object>> findCitiesByProvincesId(String pid);

    List<Map<String, Object>> findAreasByProvincesId(String pid);
}

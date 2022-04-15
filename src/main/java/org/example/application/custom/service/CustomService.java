package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.system.entity.FileInfo;

import java.util.List;
import java.util.Map;

public interface CustomService extends BaseService {

    List<FileInfo> getBanner();

    void saveBanner(List<FileInfo> banners);

    String getBaseNotice();

    void saveBaseNotice(String content);

    String getTemperatureNotice();

    void saveTemperatureNotice(String content);

    Map<String, Object> getCount();

    void saveCount(Map<String, Object> data);
}

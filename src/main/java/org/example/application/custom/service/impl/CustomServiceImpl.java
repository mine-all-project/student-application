package org.example.application.custom.service.impl;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.CustomDAO;
import org.example.application.custom.entity.Custom;
import org.example.application.custom.service.CustomService;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomServiceImpl implements CustomService {
    private final CustomDAO customDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;
    private final FileInfoService fileInfoService;

    public CustomServiceImpl(CustomDAO customDAO, UserDAO userDAO, JwtConfigure jwtConfigure, FileInfoService fileInfoService) {
        this.customDAO = customDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
        this.fileInfoService = fileInfoService;
    }

    private Custom get() {
        return customDAO.get();
    }

    @Override
    public List<FileInfo> getBanner() {
        Custom custom = get();
        return custom.getBanner();
    }

    @Override
    public void saveBanner(List<FileInfo> banners) {
        Custom custom = get();
        custom.setBanner(banners);
        customDAO.save(custom);
    }

    @Override
    public String getBaseNotice() {
        Custom custom = get();
        return custom.getBaseNotice();
    }

    @Override
    public void saveBaseNotice(String content) {
        Custom custom = get();
        custom.setBaseNotice(content);
        customDAO.save(custom);
    }

    @Override
    public String getTemperatureNotice() {
        Custom custom = get();
        return custom.getTemperatureNotice();
    }

    @Override
    public void saveTemperatureNotice(String content) {
        Custom custom = get();
        custom.setTemperatureNotice(content);
        customDAO.save(custom);
    }

    @Override
    public Map<String, Object> getCount() {
        Map<String, Object> data = new HashMap<>();
        Custom custom = get();
        data.put("addDiagnosis", custom.getAddDiagnosis());
        data.put("nowDiagnosis", custom.getNowDiagnosis());
        data.put("nowDiagnosisAdd", custom.getNowDiagnosisAdd());
        data.put("overseasDiagnosis", custom.getOverseasDiagnosis());
        data.put("overseasDiagnosisAdd", custom.getOverseasDiagnosisAdd());
        data.put("nowSuspected", custom.getNowSuspected());
        data.put("nowSuspectedAdd", custom.getNowSuspectedAdd());
        return data;
    }

    @Override
    public void saveCount(Map<String, Object> data) {
        Custom custom = get();
        String addDiagnosis = String.valueOf(data.getOrDefault("addDiagnosis",""));
        String nowDiagnosis = String.valueOf(data.getOrDefault("nowDiagnosis",""));
        String nowDiagnosisAdd = String.valueOf(data.getOrDefault("nowDiagnosisAdd",""));
        String overseasDiagnosis = String.valueOf(data.getOrDefault("overseasDiagnosis",""));
        String overseasDiagnosisAdd = String.valueOf(data.getOrDefault("overseasDiagnosisAdd",""));
        String nowSuspected = String.valueOf(data.getOrDefault("nowSuspected",""));
        String nowSuspectedAdd = String.valueOf(data.getOrDefault("nowSuspectedAdd",""));
        custom.setAddDiagnosis(addDiagnosis);
        custom.setNowDiagnosis(nowDiagnosis);
        custom.setNowDiagnosisAdd(nowDiagnosisAdd);
        custom.setOverseasDiagnosis(overseasDiagnosis);
        custom.setOverseasDiagnosisAdd(overseasDiagnosisAdd);
        custom.setNowSuspected(nowSuspected);
        custom.setNowSuspectedAdd(nowSuspectedAdd);
        customDAO.save(custom);
    }
}

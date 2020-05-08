package org.example.fangwuzulin.service.impl;

import org.example.fangwuzulin.config.ApplicationException;
import org.example.fangwuzulin.entity.*;
import org.example.fangwuzulin.form.HousesForm;
import org.example.fangwuzulin.form.LeaveMessageForm;
import org.example.fangwuzulin.mapping.AudioFilesMapping;
import org.example.fangwuzulin.mapping.HousesMapping;
import org.example.fangwuzulin.mapping.LeaveMessageMapping;
import org.example.fangwuzulin.mapping.SysUserMapping;
import org.example.fangwuzulin.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${usePhone}")
    private boolean usePhone;
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final HousesMapping housesMapping;
    private final AudioFilesMapping audioFilesMapping;
    private final LeaveMessageMapping leaveMessageMapping;
    private final SysUserMapping sysUserMapping;

    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(HousesMapping housesMapping,
                              AudioFilesMapping audioFilesMapping,
                              LeaveMessageMapping leaveMessageMapping,
                              SysUserMapping sysUserMapping) {
        this.housesMapping = housesMapping;
        this.audioFilesMapping = audioFilesMapping;
        this.leaveMessageMapping = leaveMessageMapping;
        this.sysUserMapping = sysUserMapping;
    }

    @Override
    public List<Houses> getHousesList() {
        return housesMapping.findAll().stream().map(this::setUserInfo).collect(Collectors.toList());
    }

    private Houses setUserInfo(Houses houses) {
        houses.setUser(sysUserMapping.findById(houses.getUser_id()));
        return houses;
    }

    @Override
    public List<Houses> getHousesListByTitle(String title) {
        return housesMapping.findAllByTitle(title).stream().map(this::setUserInfo).collect(Collectors.toList());
    }

    @Override
    public Houses getHousesById(String id) {
        return setUserInfo(housesMapping.getHousesById(id));
    }

    @Override
    public void removeHousesById(String id) {
        Integer count = housesMapping.removeHousesById(id);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public void saveHousesInfo(HousesForm form) {
        Houses houses = form.toEntity();
        houses.setUser_id(getUser().getId());
        if (houses.getId() == null) {
            houses.setId(UUID.randomUUID().toString());
            Integer count = housesMapping.insertHousesInfo(houses);

            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        } else {
            Integer count = housesMapping.updateHousesInfo(houses);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        }
    }

    @Override
    public AudioFiles uploadFile(HttpServletRequest request) {
        String path = getFilePath(request, filePath, virtualPath);
        AudioFiles audioFiles = new AudioFiles();
        audioFiles.setId(UUID.randomUUID().toString());
        audioFiles.setUrl(path);
        Integer count = audioFilesMapping.insertAudioFiles(audioFiles);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
        return audioFiles;
    }

    @Override
    public void saveLeaveMessage(LeaveMessageForm form) {
        Integer count = leaveMessageMapping.insertMessage(form.toEntity());
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public List<Houses> getHousesByUser() {
        SysUser sysUser = getUser();
        return housesMapping.findAllByUser(sysUser.getId()).stream().map(this::setUserInfo).collect(Collectors.toList());
    }
}

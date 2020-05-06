package org.example.fangwuzulin.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.entity.*;
import org.example.fangwuzulin.form.HousesForm;
import org.example.fangwuzulin.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RestFulService {

    default String getFilePath(HttpServletRequest request,String filePath,String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath,virtualPath);
        return fileUtils.saveFile(multipartFile);
    }
    default SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }
    List<Houses> getHousesList();

    List<Houses> getHousesListByName(String name);

    Houses getHousesById(String id);

    void removeHousesById(String id);

    void saveHousesInfo(HousesForm form);

    AudioFiles uploadFile(HttpServletRequest request);
}

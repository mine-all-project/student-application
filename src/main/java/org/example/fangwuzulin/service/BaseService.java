package org.example.fangwuzulin.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public interface BaseService {
//        default SysUser getUserInfo() {
//        Subject subject = SecurityUtils.getSubject();
//        return (SysUser) subject.getPrincipal();
//    }
    SysUser getUserInfo();

    default void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    default String getFilePath(HttpServletRequest request, String filePath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }
}

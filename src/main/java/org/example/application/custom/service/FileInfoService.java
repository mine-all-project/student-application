package org.example.application.custom.service;

import org.example.application.common.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件功能抽象接口
 */
public interface FileInfoService {
    default org.example.application.custom.FileInfo getFilePath(HttpServletRequest request, String uploadPath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }

    default MultipartFile getFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        return multipartRequest.getFile("file");
    }

    org.example.application.custom.FileInfo uploadFile(HttpServletRequest request);

    FileInfo getById(String id);
}

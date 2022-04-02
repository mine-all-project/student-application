package org.example.application.custom.service;

import org.example.application.common.utils.FileUtils;
import org.example.application.custom.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


public interface FileInfoService {
    default FileInfo getFilePath(HttpServletRequest request, String uploadPath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }

    default MultipartFile getFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        return multipartRequest.getFile("file");
    }

    FileInfo uploadFile(HttpServletRequest request);

    FileInfo getById(String id);

    FileInfo saveFileInfo(FileInfo fileInfo);
}

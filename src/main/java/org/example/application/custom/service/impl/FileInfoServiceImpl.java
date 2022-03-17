package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.utils.FileUtils;
import org.example.application.custom.dao.FileInfoDAO;
import org.example.application.custom.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件功能实现类
 */
@Slf4j
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${uploadPath}")
    private String uploadPath;

    private final org.example.application.custom.dao.FileInfoDAO fileInfoDAO;

    public FileInfoServiceImpl(FileInfoDAO fileInfoDAO) {
        this.fileInfoDAO = fileInfoDAO;
    }

    /**
     * 上传文件
     * @return
     */
    @Override
    public org.example.application.custom.FileInfo uploadFile(HttpServletRequest request) {
        MultipartFile multipartFile = getFile(request);
        FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
        org.example.application.custom.FileInfo fileInfo = fileUtils.saveFile(multipartFile);
        return saveFileInfo(fileInfo);
    }

    private org.example.application.custom.FileInfo saveFileInfo(org.example.application.custom.FileInfo fileInfo) {
        return fileInfoDAO.save(fileInfo);
    }

    @Override
    public FileInfo getById(String id) {
        return fileInfoDAO.findById(id);
    }
}

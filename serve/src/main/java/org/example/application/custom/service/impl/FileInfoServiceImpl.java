package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.utils.FileUtils;
import org.example.application.custom.dao.FileInfoDAO;
import org.example.application.custom.entity.FileInfo;
import org.example.application.custom.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${uploadPath}")
    private String uploadPath;

    private final FileInfoDAO fileInfoDAO;

    public FileInfoServiceImpl(FileInfoDAO fileInfoDAO) {
        this.fileInfoDAO = fileInfoDAO;
    }

    @Override
    public FileInfo uploadFile(HttpServletRequest request) {
        MultipartFile multipartFile = getFile(request);
        FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
        FileInfo fileInfo = fileUtils.saveFile(multipartFile);
        return saveFileInfo(fileInfo);
    }

    @Override
    public  FileInfo saveFileInfo(FileInfo fileInfo) {
        return fileInfoDAO.save(fileInfo);
    }

    @Override
    public FileInfo getById(String id) {
        return fileInfoDAO.findById(id);
    }

}

package org.example.application.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.utils.FileUtils;
import org.example.application.system.dao.FileInfoDAO;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.service.FileInfoService;
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

    private final FileInfoDAO fileInfoDAO;

    public FileInfoServiceImpl(FileInfoDAO fileInfoDAO) {
        this.fileInfoDAO = fileInfoDAO;
    }

    /**
     * 上传文件
     *
     * @return
     */
    @Override
    public FileInfo uploadFile(HttpServletRequest request) {
        MultipartFile multipartFile = getFile(request);
        if (multipartFile != null) {
            FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
            FileInfo fileInfo = fileUtils.saveFile(multipartFile);
            return saveFileInfo(fileInfo);
        }
        return new FileInfo();
    }

    private FileInfo saveFileInfo(FileInfo fileInfo) {
        return fileInfoDAO.save(fileInfo);
    }

    @Override
    public FileInfo getById(String id) {
        return fileInfoDAO.findById(id);
    }
}

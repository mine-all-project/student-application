package org.example.tuole.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.tuole.system.dao.FileInfoRepository;
import org.example.tuole.system.entity.FileInfo;
import org.example.tuole.system.service.FileService;
import org.example.tuole.system.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件功能实现类
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${uploadPath}")
    private String uploadPath;

    private final FileInfoRepository fileInfoRepository;

    public FileServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    /**
     * 上传文件
     * @return
     */
    @Override
    public FileInfo uploadFile(HttpServletRequest request) {
        MultipartFile multipartFile = getFile(request);
        FileUtils fileUtils = new FileUtils(uploadPath, virtualPath);
        FileInfo fileInfo = fileUtils.saveFile(multipartFile);
        return saveFileInfo(fileInfo);
    }

    private FileInfo saveFileInfo(FileInfo fileInfo) {
        return fileInfoRepository.saveAndFlush(fileInfo);
    }

}

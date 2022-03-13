package org.example.textreader.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.system.dao.FileInfoRepository;
import org.example.textreader.system.entity.FileInfo;
import org.example.textreader.system.service.FileInfoService;
import org.example.textreader.system.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件功能实现类
 */
@Slf4j
@Service
public class FileFileServiceImpl implements FileInfoService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${uploadPath}")
    private String uploadPath;

    private final FileInfoRepository fileInfoRepository;

    public FileFileServiceImpl(FileInfoRepository fileInfoRepository) {
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

    @Override
    public FileInfo getById(String id) {
        return fileInfoRepository.findById(id).orElse(new FileInfo());
    }
}

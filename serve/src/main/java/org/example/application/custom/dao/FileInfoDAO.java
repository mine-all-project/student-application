package org.example.application.custom.dao;

import org.example.application.custom.dao.jpa.FileInfoRepository;
import org.example.application.custom.entity.FileInfo;
import org.springframework.stereotype.Component;

@Component
public class FileInfoDAO  {
    private final FileInfoRepository fileInfoRepository;

    public FileInfoDAO(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    public FileInfo save(FileInfo fileInfo) {
        return fileInfoRepository.saveAndFlush(fileInfo);
    }

    public FileInfo findById(String id) {
        return fileInfoRepository.findById(id).orElse(new FileInfo());
    }
}

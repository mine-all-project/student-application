package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.FileInfoRepository;
import org.springframework.stereotype.Component;

@Component
public class FileInfoDAO extends BaseDAO {
    private final org.example.application.custom.dao.jpa.FileInfoRepository fileInfoRepository;

    public FileInfoDAO(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    public org.example.application.custom.FileInfo save(org.example.application.custom.FileInfo fileInfo) {
        return fileInfoRepository.saveAndFlush(fileInfo);
    }

    public org.example.application.custom.FileInfo findById(String id) {
        return fileInfoRepository.findById(id).orElse(new FileInfo());
    }
}

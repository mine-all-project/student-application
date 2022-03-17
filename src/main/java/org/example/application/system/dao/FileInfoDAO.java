package org.example.application.system.dao;

import org.example.application.common.BaseDAO;
import org.example.application.system.dao.jpa.FileInfoRepository;
import org.example.application.system.entity.FileInfo;
import org.springframework.stereotype.Component;

@Component
public class FileInfoDAO extends BaseDAO {
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

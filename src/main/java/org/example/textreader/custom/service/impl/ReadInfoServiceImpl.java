package org.example.textreader.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.common.DIC;
import org.example.textreader.custom.dao.jpa.ReadInfoRepository;
import org.example.textreader.custom.entity.ReadInfo;
import org.example.textreader.custom.service.ReadInfoService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReadInfoServiceImpl implements ReadInfoService {
    private final ReadInfoRepository readInfoRepository;

    public ReadInfoServiceImpl( ReadInfoRepository readInfoRepository) {
        this.readInfoRepository = readInfoRepository;
    }


    @Override
    public ReadInfo saveReadInfo(ReadInfo entity) {
        return readInfoRepository.saveAndFlush(entity);
    }

    @Override
    public ReadInfo successEnd(ReadInfo entity) {
        entity.setStatus(DIC.READ_INFO_SUCCESS);
        return saveReadInfo(entity);
    }

    @Override
    public ReadInfo failEnd(ReadInfo entity) {
        entity.setStatus(DIC.READ_INFO_FAIL);
        return saveReadInfo(entity);
    }

    @Override
    public ReadInfo findByIdAndStatus(String id) {
        return readInfoRepository.findByIdAndStatus(id,DIC.READ_INFO_SUCCESS).orElse(new ReadInfo());
    }
}

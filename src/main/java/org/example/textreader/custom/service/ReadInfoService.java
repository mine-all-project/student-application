package org.example.textreader.custom.service;

import org.example.textreader.custom.entity.ReadInfo;

public interface ReadInfoService {
    ReadInfo saveReadInfo(ReadInfo entity);
    ReadInfo successEnd(ReadInfo entity);
    ReadInfo failEnd(ReadInfo entity);

    ReadInfo findByIdAndStatus(String id);
}

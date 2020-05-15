package org.example.waimai.mapping;

import org.example.waimai.entity.AudioFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioFilesMapping {
    AudioFiles findById(String id);

    Integer insertAudioFiles(AudioFiles audioFiles);
}

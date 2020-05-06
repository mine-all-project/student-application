package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.AudioFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioFilesMapping {
    AudioFiles findById(String id);

    Integer insertAudioFiles(AudioFiles audioFiles);
}

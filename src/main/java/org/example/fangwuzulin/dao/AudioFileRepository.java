package org.example.fangwuzulin.dao;

import org.example.fangwuzulin.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, String> {
    List<AudioFile> findAllByKeyWordOrderBySort(String keyFlag);

    List<AudioFile> findAllByKeyWord(String video);

    List<AudioFile> findAllByKeyWordOrderByCreateTime(String keyWord);

    List<AudioFile> findAllByKeyWordAndIdNotOrderByCreateTime(String keyWord, String id);
}

package org.example.tuole.dao;

import org.example.tuole.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 媒体数据持久层
 *
 *
 * 2020/4/7 18:59
 *
 *
 *
 */
@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, String> {
    List<AudioFile> findAllByKeyWordOrderBySort(String keyFlag);

    List<AudioFile> findAllByKeyWord(String video);

    List<AudioFile> findAllByKeyWordOrderByCreateTime(String keyWord);

    List<AudioFile> findAllByKeyWordAndIdNotOrderByCreateTime(String keyWord, String id);
}

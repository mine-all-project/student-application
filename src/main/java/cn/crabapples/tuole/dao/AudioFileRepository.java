package cn.crabapples.tuole.dao;

import cn.crabapples.tuole.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 系统用户持久层
 *
 * @author Mr.He
 * 2019/7/4 1422:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, String> {
    List<AudioFile> findAllByKeyWordOrderBySort(String keyFlag);

    List<AudioFile> findAllByKeyWord(String video);

    List<AudioFile> findAllByKeyWordOrderByCreateTime(String keyWord);
}

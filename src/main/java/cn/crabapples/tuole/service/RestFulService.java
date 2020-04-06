package cn.crabapples.tuole.service;

import cn.crabapples.tuole.entity.AudioFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RestFulService {

    List<AudioFile> getPictures();

    AudioFile uploadFile(HttpServletRequest request, String id);

    AudioFile getAudioFileByKeyWord(String keyWord);

    AudioFile saveAudioFile(HttpServletRequest request, AudioFile audioFile, String id);

    List<AudioFile> getAudioFileListByKeyWord(String keyWord);

    AudioFile getAudioFileById(String id);

    void removeAudioFileById(String id);
}

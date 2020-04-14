package org.example.yangjichang.service;

import org.example.yangjichang.entity.AudioFile;
import org.example.yangjichang.entity.Message;
import org.example.yangjichang.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface RestFulService {

    List<AudioFile> getPictures();

    AudioFile uploadFile(HttpServletRequest request, String id);

    AudioFile getAudioFileByKeyWord(String keyWord);

    AudioFile saveAudioFile(HttpServletRequest request, AudioFile audioFile, String id);

    List<AudioFile> getAudioFileListByKeyWord(String keyWord);

    AudioFile getAudioFileById(String id);

    void removeAudioFileById(String id);

    List<AudioFile> getAudioFileListNot(String keyWord, String id);

    Map<String, String> uploadShopFile(HttpServletRequest request);

//    List<Goods> getGoodsList(String keyword);
//
//    Goods saveGoodsInfo(Goods goods);

    Orders submitOrder(String shop);

//    Goods getGoodsById(String id);

    Message submitMessage(Message message);

    List<Message> getMessages(Integer area);

    void removeMessageById(String id);

    void addMessage(Message message, String id);
}

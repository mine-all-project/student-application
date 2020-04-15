package org.example.yangjichang.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.yangjichang.entity.*;
import org.example.yangjichang.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface RestFulService {

    default String getfilePath(HttpServletRequest request,String filePath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath);
        return fileUtils.saveFile(multipartFile);
    }

    default SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }


    List<Animal> getAnimalList(String type);

    Animal getAnimalById(String id);

    void removeAnimalById(String id);

    void saveAnimalInfo(Animal animal);

    AudioFile uploadFile(HttpServletRequest request, String id);

    AudioFile saveAudioFile(HttpServletRequest request, AudioFile audioFile, String id);

    AudioFile getAudioFileById(String id);

    void removeAudioFileById(String id);

    Orders submitOrder(String shop);





    Map<String, String> uploadShopFile(HttpServletRequest request);


    Message submitMessage(Message message);

    List<Message> getMessages(Integer area);

    void removeMessageById(String id);

    void addMessage(Message message, String id);



}

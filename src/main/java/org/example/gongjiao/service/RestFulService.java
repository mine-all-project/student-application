package org.example.gongjiao.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.gongjiao.entity.*;
import org.example.gongjiao.form.LineesForm;
import org.example.gongjiao.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RestFulService {

    default String getFilePath(HttpServletRequest request,String filePath,String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath,virtualPath);
        return fileUtils.saveFile(multipartFile);
    }
    default SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }

    List<Linees> getLinesList();
    Linees getLinesById(String id);
    void saveLinesInfo(LineesForm form);

    List<Stands> getStandsList();

//    void removeAnimalById(String id);
//    void saveAnimalInfo(Animal animal);
//
//    List<Paper> getPapersByKeyWord(String keyWord);
//    void savePaper(Paper paper);
//    Paper getPaperById(String id);
//    void removePaperById(String id);
//
//    AudioFile uploadFile(HttpServletRequest request, String id);
//    AudioFile updateFile(HttpServletRequest request, AudioFile audioFile);
//    List<AudioFile> getFileListByKeyWord(String keyWord);
//    void removeFileById(String id);
//    AudioFile getFileById(String id);
//
//    Orders createOrder(Orders orders);
//    List<Orders> getOrdersList();
//    void removeOrdersById(String id);
//    List<Orders> getOrdersListByUser();

}

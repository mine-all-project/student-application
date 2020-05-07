package org.example.gongjiao.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.gongjiao.entity.*;
import org.example.gongjiao.form.LinesForm;
import org.example.gongjiao.form.PapersForm;
import org.example.gongjiao.form.StandsForm;
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
    void saveLinesInfo(LinesForm form);
    void removeLinesById(String id);


    List<Stands> getStandsList();
    Stands getStandsById(String id);
    void saveStandsInfo(StandsForm form);
    void removeStandsById(String id);

    List<Papers> getPapersByKeyWords(String keyWords);
    Papers getPapersById(String id);
    void savePapers(PapersForm form);
    void removePapersById(String id);

//    Orders createOrder(Orders orders);
//    List<Orders> getOrdersList();
//    void removeOrdersById(String id);
//    List<Orders> getOrdersListByUser();

}

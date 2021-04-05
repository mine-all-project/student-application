package cn.crabapples.application.service;

import cn.crabapples.common.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface FileDataService {
    default String getFilePath(HttpServletRequest request, String filePath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }

    Map<String, String> uploadFile(HttpServletRequest request);

    void removeById(String id);
}

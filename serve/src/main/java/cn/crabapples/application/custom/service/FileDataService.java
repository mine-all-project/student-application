package cn.crabapples.application.custom.service;

import cn.crabapples.application.common.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 1:49
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface FileDataService {
    default String getFilePath(HttpServletRequest request, String filePath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }

    Map<String, String> uploadFile(HttpServletRequest request);

}

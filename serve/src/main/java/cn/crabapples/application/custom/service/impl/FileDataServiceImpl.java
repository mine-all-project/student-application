package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.custom.service.FileDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
@Slf4j
@Service
public class FileDataServiceImpl implements FileDataService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${filePath}")
    private String filePath;

    @Override
    public Map<String, String> uploadFile(HttpServletRequest request) {
        String path = getFilePath(request, filePath, virtualPath);
        Map<String, String> result = new HashMap<>();
        result.put("path", path);
        return result;
    }

}

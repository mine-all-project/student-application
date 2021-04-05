package cn.crabapples.application.service.impl;

import cn.crabapples.application.dao.FileDataDAO;
import cn.crabapples.application.service.FileDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FileDataServiceImpl implements FileDataService {
    @Value("${virtualPath}")
    private String virtualPath;
    @Value("${filePath}")
    private String filePath;
    private final FileDataDAO fileDataDAO;

    public FileDataServiceImpl(FileDataDAO fileDataDAO) {
        this.fileDataDAO = fileDataDAO;
    }

    @Override
    public Map<String, String> uploadFile(HttpServletRequest request) {
        String path = getFilePath(request, filePath, virtualPath);
        Map<String, String> result = new HashMap<>();
        result.put("path", path);
        return result;
    }

    @Override
    public void removeById(String id) {
        fileDataDAO.removeById(id);
    }
}

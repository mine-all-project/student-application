package org.example.textreader.custom.service.impl;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import lombok.extern.slf4j.Slf4j;
import org.example.textreader.common.ApplicationException;
import org.example.textreader.custom.dao.jpa.PaperRepository;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.entity.ReadInfo;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.form.ReadForm;
import org.example.textreader.custom.service.PaperService;
import org.example.textreader.custom.service.ReadInfoService;
import org.example.textreader.custom.utils.TextReadUtils;
import org.example.textreader.system.entity.FileInfo;
import org.example.textreader.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PaperServiceImpl implements PaperService {
    @Value("${appKey}")
    private String appKey;
    @Value("${accessKey}")
    private String accessKey;
    @Value("${keySecret}")
    private String keySecret;
    @Value("${uploadPath}")
    private String uploadPath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final PaperRepository paperRepository;
    private final FileInfoService fileInfoService;
    private final ReadInfoService readInfoService;

    public PaperServiceImpl(PaperRepository paperRepository, FileInfoService fileInfoService, ReadInfoService readInfoService) {
        this.paperRepository = paperRepository;
        this.fileInfoService = fileInfoService;
        this.readInfoService = readInfoService;
    }

    @Override
    public List<Paper> getPaperList() {
        return paperRepository.findAll();
    }

    @Override
    public Paper savePaper(PaperForm form) {
        return paperRepository.saveAndFlush(form.toEntity());
    }

    @Override
    public Paper getPaperById(String id) {
        return paperRepository.findById(id).orElse(new Paper());
    }

    @Override
    public void deleteById(String id) {
        paperRepository.deleteById(id);
    }

    @Override
    public String getContentByFile(String id) throws IOException {
        FileInfo fileInfo = fileInfoService.getById(id);
        String filePath = fileInfo.getUploadPath();
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder content = new StringBuilder();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            content.append(line.trim());
        }
        return content.toString();
    }


    @Override
    public ReadInfo createFile(ReadForm form) throws IOException {
        AccessToken accessToken = new AccessToken(accessKey, keySecret);
        accessToken.apply();
        String token = accessToken.getToken();
        String url = ""; // 默认即可，默认值：wss://nls-gateway.cn-shanghai.aliyuncs.com/ws/v1
        TextReadUtils readUtils = new TextReadUtils(appKey, token, url);
        ReadInfo readInfo = form.toEntity();
        renderReadFileTempPath(readInfo);
//        "siyue",
        readUtils.process(readInfoService, readInfo, OutputFormatEnum.MP3);
        readUtils.shutdown();
        return readInfoService.saveReadInfo(readInfo);
    }

    @Override
    public ReadInfo getReaderFileById(String id) {
        return readInfoService.findByIdAndStatus(id);
    }

    private void renderReadFileTempPath(ReadInfo readInfo) {
        String fold = uploadPath + "temp/";
        File path = new File(fold);
        if (!path.exists()) {
            log.debug("目录不存在,创建目录[{}]", path.getPath());
            if (!path.mkdirs()) {
                log.error("文件保存失败: 目录创建失败");
                throw new ApplicationException("文件保存失败: 目录创建失败");
            }
        }
        UUID id = UUID.randomUUID();
        String filePath = MessageFormat.format("{0}{1}.mp3", fold, id);
        String virtualPath = MessageFormat.format("{0}temp/{1}.mp3",this.virtualPath, id);
        readInfo.setFilePath(filePath);
        readInfo.setVirtualPath(virtualPath);
    }
}

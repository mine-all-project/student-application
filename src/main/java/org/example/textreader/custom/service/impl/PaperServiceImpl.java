package org.example.textreader.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.custom.dao.jpa.PaperRepository;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.service.PaperService;
import org.example.textreader.system.entity.FileInfo;
import org.example.textreader.system.service.FileInfoService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Slf4j
@Service
public class PaperServiceImpl implements PaperService {
    private final PaperRepository paperRepository;
    private final FileInfoService fileInfoService;

    public PaperServiceImpl(PaperRepository paperRepository, FileInfoService fileInfoService) {
        this.paperRepository = paperRepository;
        this.fileInfoService = fileInfoService;
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
}

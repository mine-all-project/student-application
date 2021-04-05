package cn.crabapples.application.dao;

import cn.crabapples.application.dao.jpa.FileDataRepository;
import cn.crabapples.application.entity.FileData;
import cn.crabapples.application.form.FileForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileDataDAO {
    private final FileDataRepository fileDataRepository;

    public FileDataDAO(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    public List<FileData> getListByKeywords(String keywords) {
        Sort sort = Sort.by(Sort.Order.asc("createTime"));
        return fileDataRepository.findByKeywords(keywords, sort);
    }

    public synchronized void save(List<FileForm> form) {
        FileData file = new FileData();
        List<FileData> data = new ArrayList<>(form.size());
        form.forEach(e -> {
            BeanUtils.copyProperties(e, file);
            try {
                FileData obj = (FileData) file.clone();
                data.add(obj);
            } catch (CloneNotSupportedException exception) {
                log.info("clone出现异常:[{}]", exception.getMessage(), exception);
            }
        });
        fileDataRepository.saveAll(data);
    }

    public void removeById(String id) {
        fileDataRepository.deleteById(id);
    }
}

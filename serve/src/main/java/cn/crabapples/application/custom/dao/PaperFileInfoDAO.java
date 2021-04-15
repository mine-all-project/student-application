package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.PapersFileInfoRepository;
import cn.crabapples.application.custom.entity.PaperFileInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章文件数据库交互实现类
 */
@Component
public class PaperFileInfoDAO extends BaseDAO {
    private final PapersFileInfoRepository papersFileInfoRepository;

    public PaperFileInfoDAO(PapersFileInfoRepository papersFileInfoRepository) {
        this.papersFileInfoRepository = papersFileInfoRepository;
    }

    public void save(PaperFileInfo entity) {
        papersFileInfoRepository.saveAndFlush(entity);
    }

    public void saveAll(List<PaperFileInfo> entityList) {
        papersFileInfoRepository.saveAll(entityList);
    }
}

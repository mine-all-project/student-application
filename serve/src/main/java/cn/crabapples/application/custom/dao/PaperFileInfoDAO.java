package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.PapersFileInfoRepository;
import cn.crabapples.application.custom.entity.PaperFileInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 23:00
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
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

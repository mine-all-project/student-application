package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.PapersRepository;
import cn.crabapples.application.custom.entity.Papers1;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章文件数据库交互实现类（mysql）
 */
@Component
public class PapersDAO1 extends BaseDAO {
    private final PapersRepository papersRepository;

    public PapersDAO1(PapersRepository papersRepository) {
        this.papersRepository = papersRepository;
    }

    public List<Papers1> getAll() {
        return papersRepository.findAllByDelFlag(ascByCreateTime, NOT_DEL);
    }

    public void removeById(String id) {
        Papers1 papers = papersRepository.findByIdAndDelFlag(id, NOT_DEL);
        papers.setDelFlag(1);
        papersRepository.saveAndFlush(papers);
    }

    public Papers1 savePapers(Papers1 entity) {
        return papersRepository.saveAndFlush(entity);
    }

    public Papers1 findById(String id) {
        return papersRepository.findByIdAndDelFlag(id, NOT_DEL);
    }
}

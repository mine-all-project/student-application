package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.PapersRepository;
import cn.crabapples.application.custom.entity.Papers1;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 15:04
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
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
}

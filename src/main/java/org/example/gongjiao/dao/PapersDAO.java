package org.example.gongjiao.dao;

import org.example.gongjiao.dao.jpa.PapersRepository;
import org.example.gongjiao.entity.Papers;
import org.example.gongjiao.form.PapersForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PapersDAO {
    private final PapersRepository papersRepository;

    public PapersDAO(PapersRepository papersRepository) {
        this.papersRepository = papersRepository;
    }

    public List<Papers> findAllByKeyWords(String keyWord) {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return papersRepository.findByKeyWords(keyWord, sort);
    }

    public Papers findById(String id) {
        return papersRepository.findById(id).orElse(new Papers());
    }

    public void savePapers(PapersForm form) {
        papersRepository.saveAndFlush(form.toEntity());
    }

    public void removePapersById(String id) {
        papersRepository.deleteById(id);
    }
}

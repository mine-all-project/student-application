package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.PaperRepository;
import org.example.application.custom.entity.Paper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaperDAO extends BaseDAO {
    private final PaperRepository paperRepository;

    public PaperDAO(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public Paper savePaper(Paper entity) {
        return paperRepository.saveAndFlush(entity);
    }

    public Paper findPaperById(String id) {
        return paperRepository.findById(id).orElse(new Paper());
    }

    public void deletePaperById(String id) {
        paperRepository.deleteById(id);
    }


    public List<Paper> getAll() {
        return paperRepository.findAll();
    }

    public List<Paper> getByTitle(String keyword) {
        return paperRepository.findAllByTitleContains(keyword);
    }
}

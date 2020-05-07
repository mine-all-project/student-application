package org.example.gongjiao.dao;

import org.example.gongjiao.dao.jpa.LinesRepository;
import org.example.gongjiao.dao.jpa.StandsRepository;
import org.example.gongjiao.entity.Linees;
import org.example.gongjiao.form.LineesForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinesDAO {
    private final StandsRepository standsRepository;
    private final LinesRepository linesRepository;

    public LinesDAO(StandsRepository standsRepository, LinesRepository linesRepository) {
        this.standsRepository = standsRepository;
        this.linesRepository = linesRepository;
    }

    public List<Linees> findAll() {
        return linesRepository.findAll();
    }

    public Linees findById(String id) {
        return linesRepository.findById(id).orElse(new Linees());
    }

    public void saveLines(LineesForm form) {
        linesRepository.saveAndFlush(form.toEntity());
    }

    public void removeLinesById(String id) {
        linesRepository.deleteById(id);
    }
}

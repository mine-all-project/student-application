package org.example.textreader.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.custom.dao.jpa.PaperRepository;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.service.PaperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaperServiceImpl implements PaperService {
    private final PaperRepository paperRepository;

    public PaperServiceImpl(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public List<Paper> getPaperList() {
        return paperRepository.findAll();
    }

    @Override
    public Paper addPaper(PaperForm form) {
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
}

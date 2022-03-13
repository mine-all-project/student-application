package org.example.textreader.custom.service;

import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.form.PaperForm;

import java.util.List;

public interface PaperService {
    List<Paper> getPaperList();

    Paper addPaper(PaperForm form);

    Paper getPaperById(String id);

    void deleteById(String id);
}

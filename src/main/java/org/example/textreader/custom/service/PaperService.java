package org.example.textreader.custom.service;

import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.entity.ReadInfo;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.form.ReadForm;

import java.io.IOException;
import java.util.List;

public interface PaperService {
    List<Paper> getPaperList();

    Paper savePaper(PaperForm form);

    Paper getPaperById(String id);

    void deleteById(String id);

    String getContentByFile(String file) throws IOException;

    ReadInfo createFile(ReadForm form) throws IOException;

    ReadInfo getReaderFileById(String id);
}

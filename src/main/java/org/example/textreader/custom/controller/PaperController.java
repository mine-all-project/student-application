package org.example.textreader.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.textreader.common.ResponseDTO;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.form.PaperForm;
import org.example.textreader.custom.service.PaperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/paper")
public class PaperController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        final List<Paper> paperList = paperService.getPaperList();
        return ResponseDTO.returnSuccess(paperList);
    }
    @GetMapping("/id/{id}")
    public ResponseDTO getById(@PathVariable String id) {
        Paper paper  = paperService.getPaperById(id);
        return ResponseDTO.returnSuccess(paper);
    }

    @PostMapping("/add")
    public ResponseDTO add(PaperForm form) {
        Paper paper = paperService.addPaper(form);
        return ResponseDTO.returnSuccess(paper);
    }

    @PostMapping("/del/{id}")
    public ResponseDTO deleteById(@PathVariable String id) {
        paperService.deleteById(id);
        return ResponseDTO.returnSuccess();
    }
}

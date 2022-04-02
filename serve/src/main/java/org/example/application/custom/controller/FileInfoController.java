package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.ResponseDTO;
import org.example.application.custom.entity.FileInfo;
import org.example.application.custom.service.FileInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件接口
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class FileInfoController extends BaseController {

    private final FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @RequestMapping("/uploadFile")
    public ResponseDTO uploadFile(HttpServletRequest request) {
        FileInfo fileInfo = fileInfoService.uploadFile(request);
        return ResponseDTO.returnSuccess("success", fileInfo);
    }
}

package org.example.tuole.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tuole.common.BaseController;
import org.example.tuole.system.dto.ResponseDTO;
import org.example.tuole.system.entity.FileInfo;
import org.example.tuole.system.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件接口
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class FileController extends BaseController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/uploadFile")
    public ResponseDTO uploadFile(HttpServletRequest request) {
        log.info("收到请求->上传文件");
        FileInfo fileInfo = fileService.uploadFile(request);
        log.info("返回结果->上传文件结束:[{}]", fileInfo);
        return ResponseDTO.returnSuccess("上传成功", fileInfo);
    }
}

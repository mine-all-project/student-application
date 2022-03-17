package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.dto.ResponseDTO;
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

    private final org.example.application.custom.service.FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @RequestMapping("/uploadFile")
    public org.example.application.custom.dto.ResponseDTO uploadFile(HttpServletRequest request) {
        log.info("收到请求->上传文件");
        FileInfo fileInfo = fileInfoService.uploadFile(request);
        log.info("返回结果->上传文件结束:[{}]", fileInfo);
        return ResponseDTO.returnSuccess("上传成功", fileInfo);
    }
}

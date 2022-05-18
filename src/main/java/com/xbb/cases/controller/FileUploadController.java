package com.xbb.cases.controller;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.PathConstant;
import com.xbb.cases.util.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileUploadController {

    /**
     * 图片上传
     */
    @RequestMapping(value = "/uploadImgFile", produces = "application/json;charset=UTF-8")
    public RespResult uploadImgFile(@RequestParam("file") MultipartFile multipartFile) {
        RespResult respResult = new RespResult();
        if (multipartFile != null) {
            String suffix = FileUtils.fileSuffix(multipartFile.getOriginalFilename()); // 文件名后缀
            String tmpFileName = FileUtils.createTmpFileName(suffix); // 生成保证不重复的临时文件名
            File tmpFile = new File(PathConstant.FILE_STORAGE_ROOT, tmpFileName);
            try {
                multipartFile.transferTo(tmpFile); // 写入项目存储位置
                respResult.success("/images/" + tmpFileName);
            } catch (IOException e) {
                respResult.setErrorMessage("上传失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
        return respResult;
    }

}

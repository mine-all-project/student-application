package cn.crabapples.system.controller;

import cn.crabapples.common.BaseController;
import cn.crabapples.common.utils.jwt.JwtIgnore;
import cn.crabapples.system.dto.ResponseDTO;
import cn.crabapples.system.service.FileDataService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@RestController
@Api("文件接口")
@Slf4j
@RequestMapping("/api")
public class FileDataController extends BaseController {

    private final FileDataService fileDataService;

    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @JwtIgnore
    @RequestMapping("/uploadFile")
    public ResponseDTO uploadFile(HttpServletRequest request) {
        log.info("收到请求->上传文件");
        Map<String, String> filePath = fileDataService.uploadFile(request);
        log.info("返回结果->上传文件结束:[{}]", filePath);
        return ResponseDTO.returnSuccess("上传成功", filePath);
    }


//    @RequestMapping("/saveFileData")
//    public ResponseDTO savePapers(@RequestBody List<FileForm> form) {
//        log.info("收到请求->保存文件:[{}]", form);
//        fileDataService.saveFile(form);
//        log.info("返回结果->保存文件完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }

}

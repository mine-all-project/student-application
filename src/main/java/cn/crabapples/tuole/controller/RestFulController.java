package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.AudioFile;
import cn.crabapples.tuole.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    @RequestMapping("/uploadFile/{id}")
    @ResponseBody
    public ResponseDTO uploadFile(HttpServletRequest request, @PathVariable("id") String id) {
        logger.info("收到请求->上传文件:[{}]", id);
        AudioFile audioFile = restFulService.uploadFile(request, id);
        logger.info("文件上传完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    @PostMapping("/saveAudioFile/{id}")
    public ResponseDTO saveAudioFile(HttpServletRequest request, @RequestBody AudioFile audioFile, @PathVariable("id") String id) {
        logger.info("收到请求->保存媒体数据:[{}]", id);
        audioFile = restFulService.saveAudioFile(request, audioFile, id);
        logger.info("返回结果->保存媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    @GetMapping("/getPictures")
    public ResponseDTO getPictures() {
        logger.info("收到请求->获取美景图库图片");
        List<AudioFile> pictures = restFulService.getPictures();
        logger.info("返回结果->获取美景图库图片完成:[{}]", pictures);
        return ResponseDTO.returnSuccess("操作成功", pictures);
    }

    @GetMapping("/getAudioFile/{keyWord}")
    public ResponseDTO getAudioFileByKeyWord(@PathVariable("keyWord") String keyWord) {
        logger.info("收到请求->获取媒体数据,keyWord:[{}]", keyWord);
        AudioFile audioFile = restFulService.getAudioFileByKeyWord(keyWord);
        logger.info("返回结果->获取媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    @GetMapping("/getAudioFileList/{keyWord}")
    public ResponseDTO getAudioFileListByKeyWord(@PathVariable("keyWord") String keyWord) {
        logger.info("收到请求->获取媒体数据列表,keyWord:[{}]", keyWord);
        List<AudioFile> audioFiles = restFulService.getAudioFileListByKeyWord(keyWord);
        logger.info("返回结果->获取媒体数据列表完成:[{}]", audioFiles);
        return ResponseDTO.returnSuccess("操作成功", audioFiles);
    }

 @GetMapping("/getAudioFileListNot/{keyWord}/{id}")
    public ResponseDTO getAudioFileListNot(@PathVariable("keyWord") String keyWord,@PathVariable("id") String id) {
        logger.info("收到请求->获取媒体数据列表,keyword:[{}],排除id:[{}]", keyWord,id);
        List<AudioFile> audioFiles = restFulService.getAudioFileListNot(keyWord,id);
        logger.info("返回结果->获取媒体数据列表完成:[{}]", audioFiles);
        return ResponseDTO.returnSuccess("操作成功", audioFiles);
    }

    @GetMapping("/getAudioFileById/{id}")
    public ResponseDTO getAudioFileById(@PathVariable("id") String id) {
        logger.info("收到请求->获取媒体数据,id:[{}]", id);
        AudioFile audioFile = restFulService.getAudioFileById(id);
        logger.info("返回结果->获取媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    @DeleteMapping("/removeAudioFileById/{id}")
    public ResponseDTO removeAudioFileById(@PathVariable("id") String id) {
        logger.info("收到请求->删除媒体数据,id:[{}]", id);
        restFulService.removeAudioFileById(id);
        logger.info("返回结果->删除媒体数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.dto.ResponseDTO;
import cn.crabapples.tuole.entity.Picture;
import cn.crabapples.tuole.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    final
    RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    private Logger logger = LoggerFactory.getLogger(RestFulController.class);
    private static final String PAGE = "page/";

    @GetMapping("/getPictures")
    public ResponseDTO getPictures() {
        List<Picture> pictures = restFulService.getPictures();
        logger.info("收到请求->获取美景图库图片");
        return null;
    }

}

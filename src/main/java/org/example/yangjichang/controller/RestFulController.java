package org.example.yangjichang.controller;

import org.example.yangjichang.dto.ResponseDTO;
import org.example.yangjichang.entity.*;
import org.example.yangjichang.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    @RequestMapping("/getAnimalList/{type}")
    @ResponseBody
    public ResponseDTO getAnimalList(@PathVariable("type") String type) {
        logger.info("收到请求->获取商品信息:[{}]", type);
        List<Animal> animals = restFulService.getAnimalList(type);
        logger.info("返回结果->获取商品信息完成:[{}]", animals);
        return ResponseDTO.returnSuccess("操作成功", animals);
    }

    @GetMapping("/getAnimalById/{id}")
    public ResponseDTO getAnimalById(@PathVariable("id") String id) {
        logger.info("收到请求->获取商品数据,id:[{}]", id);
        Animal animal = restFulService.getAnimalById(id);
        logger.info("返回结果->获取商品数据完成:[{}]", animal);
        return ResponseDTO.returnSuccess("操作成功", animal);
    }

    @DeleteMapping("/removeAnimalById/{id}")
    public ResponseDTO removeAnimalById(@PathVariable("id") String id) {
        logger.info("收到请求->删除商品,id:[{}]", id);
        restFulService.removeAnimalById(id);
        logger.info("返回结果->删除商品完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/saveAnimalInfo")
    @ResponseBody
    public ResponseDTO saveAnimalInfo(@RequestBody Animal animal) {
        logger.info("收到请求->保存商品信息:[{}]", animal);
        restFulService.saveAnimalInfo(animal);
        logger.info("返回结果->保存商品信息完成:[{}]", animal);
        return ResponseDTO.returnSuccess("操作成功", animal);
    }

    @GetMapping("/getPapersByKeyWord/{keyWord}")
    public ResponseDTO getPapersByKeyWord(@PathVariable String keyWord) {
        logger.info("收到请求->获取文章列表,keyWord:[{}]", keyWord);
        List<Paper> papers = restFulService.getPapersByKeyWord(keyWord);
        logger.info("返回结果->获取文章列表完成:[{}]", papers);
        return ResponseDTO.returnSuccess("操作成功", papers);
    }
    @PostMapping("/savePaper")
    public ResponseDTO savePaper(@RequestBody Paper paper) {
        logger.info("收到请求->保存文章数据:[{}]", paper);
        restFulService.savePaper(paper);
        logger.info("返回结果->保存文章数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }
    @GetMapping("/getPaperById")
    public ResponseDTO getPaperById(String id) {
        logger.info("收到请求->获取文章信息,id:[{}]", id);
        Paper paper = restFulService.getPaperById(id);
        logger.info("返回结果->获取文章信息完成:[{}]", paper);
        return ResponseDTO.returnSuccess("操作成功", paper);
    }
    @DeleteMapping("/removePaperById/{id}")
    public ResponseDTO removePaperById(@PathVariable("id") String id) {
        logger.info("收到请求->删除文章数据,id:[{}]", id);
        restFulService.removePaperById(id);
        logger.info("返回结果->删除文章数据完成");
        return ResponseDTO.returnSuccess("操作成功");
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

    @GetMapping("/getAudioFileById/{id}")
    public ResponseDTO getAudioFileById(@PathVariable("id") String id) {
        logger.info("收到请求->获取媒体数据,id:[{}]", id);
        AudioFile audioFile = restFulService.getAudioFileById(id);
        logger.info("返回结果->获取媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }



    @RequestMapping("/submitOrder/{ticketsId}")
    @ResponseBody
    public ResponseDTO submitOrder(@PathVariable("ticketsId") String ticketsId) {
        logger.info("收到请求->提交订单:[{}]", ticketsId);
        Orders orders = restFulService.submitOrder(ticketsId);
        logger.info("返回结果->提交订单完成:[{}]", orders);
        return ResponseDTO.returnSuccess("操作成功", orders);
    }


    @RequestMapping("/uploadShopFile/{keyword}")
    @ResponseBody
    public ResponseDTO uploadShopFile(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        logger.info("收到请求->上传商品图片:[{}]", keyword);
        Map<String, String> path = restFulService.uploadShopFile(request);
        logger.info("返回结果->商品图片上传完成:[{}]", path);
        return ResponseDTO.returnSuccess("操作成功", path);
    }


    @RequestMapping("/getMessages/{area}")
    @ResponseBody
    public ResponseDTO getMessages(@PathVariable Integer area) {
        logger.info("收到请求->获取评论:[{}]", area);
        List<Message> message = restFulService.getMessages(area);
        logger.info("返回结果->获取评论完成:[{}]", message);
        return ResponseDTO.returnSuccess("操作成功", message);
    }

    @RequestMapping("/submitMessage")
    @ResponseBody
    public ResponseDTO submitMessage(@RequestBody Message message) {
        logger.info("收到请求->发布评论:[{}]", message);
        message = restFulService.submitMessage(message);
        logger.info("返回结果->发布评论完成:[{}]", message);
        return ResponseDTO.returnSuccess("操作成功", message);
    }

    @DeleteMapping("/removeMessageById/{id}")
    public ResponseDTO removeMessageById(@PathVariable("id") String id) {
        logger.info("收到请求->删除评论,id:[{}]", id);
        restFulService.removeMessageById(id);
        logger.info("返回结果->删除评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/addMessage/{id}")
    @ResponseBody
    public ResponseDTO addMessage(@RequestBody Message message, @PathVariable("id") String id) {
        logger.info("收到请求->回复评论:[{}],id:[{}]", message, id);
        restFulService.addMessage(message, id);
        logger.info("返回结果->回复评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }
}

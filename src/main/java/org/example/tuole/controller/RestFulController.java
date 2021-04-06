package org.example.tuole.controller;

import org.example.tuole.dto.ResponseDTO;
import org.example.tuole.entity.AudioFile;
import org.example.tuole.entity.Goods;
import org.example.tuole.entity.Message;
import org.example.tuole.entity.Orders;
import org.example.tuole.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    /**
     * 上传文件
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/uploadFile/{id}")
    @ResponseBody
    public ResponseDTO uploadFile(HttpServletRequest request, @PathVariable("id") String id) {
        logger.info("收到请求->上传文件:[{}]", id);
        AudioFile audioFile = restFulService.uploadFile(request, id);
        logger.info("文件上传完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    /**
     * 保存文件信息
     * @param request
     * @param audioFile
     * @param id
     * @return
     */
    @PostMapping("/saveAudioFile/{id}")
    public ResponseDTO saveAudioFile(HttpServletRequest request, @RequestBody AudioFile audioFile, @PathVariable("id") String id) {
        logger.info("收到请求->保存媒体数据:[{}]", id);
        audioFile = restFulService.saveAudioFile(request, audioFile, id);
        logger.info("返回结果->保存媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    /**
     * 获取美景图库页面的图片
     * @return
     */
    @GetMapping("/getPictures")
    public ResponseDTO getPictures() {
        logger.info("收到请求->获取美景图库图片");
        List<AudioFile> pictures = restFulService.getPictures();
        logger.info("返回结果->获取美景图库图片完成:[{}]", pictures);
        return ResponseDTO.returnSuccess("操作成功", pictures);
    }

    /**
     * 获取某一个文件(图片和视频)的详细信息
     * @param keyWord
     * @return
     */
    @GetMapping("/getAudioFile/{keyWord}")
    public ResponseDTO getAudioFileByKeyWord(@PathVariable("keyWord") String keyWord) {
        logger.info("收到请求->获取媒体数据,keyWord:[{}]", keyWord);
        AudioFile audioFile = restFulService.getAudioFileByKeyWord(keyWord);
        logger.info("返回结果->获取媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    /**
     * 获取文件(图片和视频)的列表
     * @param keyWord
     * @return
     */
    @GetMapping("/getAudioFileList/{keyWord}")
    public ResponseDTO getAudioFileListByKeyWord(@PathVariable("keyWord") String keyWord) {
        logger.info("收到请求->获取媒体数据列表,keyWord:[{}]", keyWord);
        List<AudioFile> audioFiles = restFulService.getAudioFileListByKeyWord(keyWord);
        logger.info("返回结果->获取媒体数据列表完成:[{}]", audioFiles);
        return ResponseDTO.returnSuccess("操作成功", audioFiles);
    }

    /**
     * 获取文件(图片和视频)的列表
     * @param keyWord
     * @param id
     * @return
     */
    @GetMapping("/getAudioFileListNot/{keyWord}/{id}")
    public ResponseDTO getAudioFileListNot(@PathVariable("keyWord") String keyWord, @PathVariable("id") String id) {
        logger.info("收到请求->获取媒体数据列表,keyword:[{}],排除id:[{}]", keyWord, id);
        List<AudioFile> audioFiles = restFulService.getAudioFileListNot(keyWord, id);
        logger.info("返回结果->获取媒体数据列表完成:[{}]", audioFiles);
        return ResponseDTO.returnSuccess("操作成功", audioFiles);
    }

    /**
     * 获取文件的详细信息
     * @param id
     * @return
     */
    @GetMapping("/getAudioFileById/{id}")
    public ResponseDTO getAudioFileById(@PathVariable("id") String id) {
        logger.info("收到请求->获取媒体数据,id:[{}]", id);
        AudioFile audioFile = restFulService.getAudioFileById(id);
        logger.info("返回结果->获取媒体数据完成:[{}]", audioFile);
        return ResponseDTO.returnSuccess("操作成功", audioFile);
    }

    /**
     * 删除图片(视频之类的)文件
     * @param id
     * @return
     */
    @DeleteMapping("/removeAudioFileById/{id}")
    public ResponseDTO removeAudioFileById(@PathVariable("id") String id) {
        logger.info("收到请求->删除媒体数据,id:[{}]", id);
        restFulService.removeAudioFileById(id);
        logger.info("返回结果->删除媒体数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    /**
     * 获取某一件商品的详细信息
     * @param id
     * @return
     */
    @GetMapping("/getGoodsById/{id}")
    public ResponseDTO getGoodsById(@PathVariable("id") String id) {
        logger.info("收到请求->获取商品数据,id:[{}]", id);
        Goods goods = restFulService.getGoodsById(id);
        logger.info("返回结果->获取商品数据完成:[{}]", goods);
        return ResponseDTO.returnSuccess("操作成功", goods);
    }

    /**
     * 上传商品的图片
     * @param request
     * @param keyword
     * @return
     */
    @RequestMapping("/uploadShopFile/{keyword}")
    @ResponseBody
    public ResponseDTO uploadShopFile(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        logger.info("收到请求->上传商品图片:[{}]", keyword);
        Map<String, String> path = restFulService.uploadShopFile(request);
        logger.info("返回结果->商品图片上传完成:[{}]", path);
        return ResponseDTO.returnSuccess("操作成功", path);
    }

    /**
     * 获取商品的列表
     * @param keyword
     * @return
     */
    @RequestMapping("/getGoodsList/{keyword}")
    @ResponseBody
    public ResponseDTO getGoodsList(@PathVariable("keyword") String keyword) {
        logger.info("收到请求->获取商品信息:[{}]", keyword);
        List<Goods> goods = restFulService.getGoodsList(keyword);
        logger.info("返回结果->获取商品信息完成:[{}]", goods);
        return ResponseDTO.returnSuccess("操作成功", goods);
    }

    /**
     * 保存商品的信息
     * @param goods
     * @return
     */
    @RequestMapping("/saveGoodsInfo")
    @ResponseBody
    public ResponseDTO saveGoodsInfo(@RequestBody Goods goods) {
        logger.info("收到请求->保存商品信息:[{}]", goods);
        goods = restFulService.saveGoodsInfo(goods);
        logger.info("返回结果->保存商品信息完成:[{}]", goods);
        return ResponseDTO.returnSuccess("操作成功", goods);
    }

    /**
     * 提交购物的订单
     * @param ticketsId
     * @return
     */
    @RequestMapping("/submitOrder/{ticketsId}")
    @ResponseBody
    public ResponseDTO submitOrder(@PathVariable("ticketsId") String ticketsId) {
        logger.info("收到请求->提交订单:[{}]", ticketsId);
        Orders orders = restFulService.submitOrder(ticketsId);
        logger.info("返回结果->提交订单完成:[{}]", orders);
        return ResponseDTO.returnSuccess("操作成功", orders);
    }

    /**
     * 管理员查看评论列表
     * @param area
     * @return
     */
    @RequestMapping("/getMessages/{area}")
    @ResponseBody
    public ResponseDTO getMessages(@PathVariable Integer area) {
        logger.info("收到请求->获取评论:[{}]", area);
        List<Message> message = restFulService.getMessages(area);
        logger.info("返回结果->获取评论完成:[{}]", message);
        return ResponseDTO.returnSuccess("操作成功", message);
    }

    /**
     * 用户发布评论
     * @param message
     * @return
     */
    @RequestMapping("/submitMessage")
    @ResponseBody
    public ResponseDTO submitMessage(@RequestBody Message message) {
        logger.info("收到请求->发布评论:[{}]", message);
        message = restFulService.submitMessage(message);
        logger.info("返回结果->发布评论完成:[{}]", message);
        return ResponseDTO.returnSuccess("操作成功", message);
    }

    /**
     * 管理员删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/removeMessageById/{id}")
    public ResponseDTO removeMessageById(@PathVariable("id") String id) {
        logger.info("收到请求->删除评论,id:[{}]", id);
        restFulService.removeMessageById(id);
        logger.info("返回结果->删除评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    /**
     * 管理员回复评论
     * @param message
     * @param id
     * @return
     */
    @RequestMapping("/addMessage/{id}")
    @ResponseBody
    public ResponseDTO addMessage(@RequestBody Message message, @PathVariable("id") String id) {
        logger.info("收到请求->回复评论:[{}],id:[{}]", message, id);
        restFulService.addMessage(message, id);
        logger.info("返回结果->回复评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }
}
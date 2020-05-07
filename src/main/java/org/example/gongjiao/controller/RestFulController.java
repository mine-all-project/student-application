package org.example.gongjiao.controller;

import org.example.gongjiao.dto.ResponseDTO;
import org.example.gongjiao.entity.Linees;
import org.example.gongjiao.entity.Stands;
import org.example.gongjiao.form.LineesForm;
import org.example.gongjiao.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    @RequestMapping("/getLinesList")
    @ResponseBody
    public ResponseDTO getLinesList() {
        logger.info("收到请求->获取线路列表");
        List<Linees> lines = restFulService.getLinesList();
        logger.info("返回结果->获取线路信息完成:[{}]", lines);
        return ResponseDTO.returnSuccess("操作成功", lines);
    }

    @GetMapping("/getLinesById")
    public ResponseDTO getLinesById(String id) {
        logger.info("收到请求->获取线路数据,id:[{}]", id);
        Linees lines = restFulService.getLinesById(id);
        logger.info("返回结果->获取线路数据完成:[{}]", lines);
        return ResponseDTO.returnSuccess("操作成功", lines);
    }

    @RequestMapping("/saveLinesInfo")
    @ResponseBody
    public ResponseDTO saveLinesInfo(@RequestBody LineesForm form) {
        logger.info("收到请求->保存线路信息:[{}]", form);
        restFulService.saveLinesInfo(form);
        logger.info("返回结果->保存线路信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

    @DeleteMapping("/removeLinesById/{id}")
    public ResponseDTO removeLinesById(@PathVariable("id") String id) {
        logger.info("收到请求->删除线路,id:[{}]", id);
        restFulService.removeLinesById(id);
        logger.info("返回结果->删除线路完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/getStandsList")
    @ResponseBody
    public ResponseDTO getStandsList() {
        logger.info("收到请求->获取站点列表]");
        List<Stands> stands = restFulService.getStandsList();
        logger.info("返回结果->获取站点列表完成:[{}]", stands);
        return ResponseDTO.returnSuccess("操作成功", stands);
    }


//
//    @GetMapping("/getPapersByKeyWord/{keyWord}")
//    public ResponseDTO getPapersByKeyWord(@PathVariable String keyWord) {
//        logger.info("收到请求->获取文章列表,keyWord:[{}]", keyWord);
//        List<Paper> papers = restFulService.getPapersByKeyWord(keyWord);
//        logger.info("返回结果->获取文章列表完成:[{}]", papers);
//        return ResponseDTO.returnSuccess("操作成功", papers);
//    }
//
//    @PostMapping("/savePaper")
//    public ResponseDTO savePaper(@RequestBody Paper paper) {
//        logger.info("收到请求->保存文章数据:[{}]", paper);
//        restFulService.savePaper(paper);
//        logger.info("返回结果->保存文章数据完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @GetMapping("/getPaperById")
//    public ResponseDTO getPaperById(String id) {
//        logger.info("收到请求->获取文章信息,id:[{}]", id);
//        Paper paper = restFulService.getPaperById(id);
//        logger.info("返回结果->获取文章信息完成:[{}]", paper);
//        return ResponseDTO.returnSuccess("操作成功", paper);
//    }
//
//    @DeleteMapping("/removePaperById/{id}")
//    public ResponseDTO removePaperById(@PathVariable("id") String id) {
//        logger.info("收到请求->删除文章数据,id:[{}]", id);
//        restFulService.removePaperById(id);
//        logger.info("返回结果->删除文章数据完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @RequestMapping("/uploadFile/{id}")
//    @ResponseBody
//    public ResponseDTO uploadFile(HttpServletRequest request, @PathVariable("id") String id) {
//        logger.info("收到请求->上传文件:[{}]", id);
//        AudioFile audioFile = restFulService.uploadFile(request, id);
//        logger.info("文件上传完成:[{}]", audioFile);
//        return ResponseDTO.returnSuccess("操作成功", audioFile);
//    }
//
//    @PostMapping("/updateFile")
//    public ResponseDTO updateFile(HttpServletRequest request, @RequestBody AudioFile audioFile) {
//        logger.info("收到请求->更新文件数据");
//        audioFile = restFulService.updateFile(request, audioFile);
//        logger.info("返回结果->更新文件数据完成:[{}]", audioFile);
//        return ResponseDTO.returnSuccess("操作成功", audioFile);
//    }
//
//    @GetMapping("/getFileListByKeyWord/{keyWord}")
//    public ResponseDTO getFileListByKeyWord(@PathVariable("keyWord") String keyWord) {
//        logger.info("收到请求->获取文件数据,keyWord:[{}]", keyWord);
//        List<AudioFile> audioFiles = restFulService.getFileListByKeyWord(keyWord);
//        logger.info("返回结果->获取文件数据完成:[{}]", audioFiles);
//        return ResponseDTO.returnSuccess("操作成功", audioFiles);
//    }
//
//    @DeleteMapping("/removeFileById/{id}")
//    public ResponseDTO removeFileById(@PathVariable("id") String id) {
//        logger.info("收到请求->删除文件数据,id:[{}]", id);
//        restFulService.removeFileById(id);
//        logger.info("返回结果->删除文件数据完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @GetMapping("/getFileById/{id}")
//    public ResponseDTO getFileById(@PathVariable("id") String id) {
//        logger.info("收到请求->获取文件数据,id:[{}]", id);
//        AudioFile audioFile = restFulService.getFileById(id);
//        logger.info("返回结果->获取文件数据完成:[{}]", audioFile);
//        return ResponseDTO.returnSuccess("操作成功", audioFile);
//    }
//
//
//    @RequestMapping("/createOrder")
//    @ResponseBody
//    public ResponseDTO createOrder(@RequestBody Orders orders) {
//        logger.info("收到请求->提交订单:[{}]", orders);
//        Orders newOrders = restFulService.createOrder(orders);
//        logger.info("返回结果->提交订单完成:[{}]", newOrders);
//        return ResponseDTO.returnSuccess("操作成功", orders);
//    }
//
//    @RequestMapping("/getOrdersList")
//    @ResponseBody
//    public ResponseDTO getOrdersList() {
//        logger.info("收到请求->获取订单信息");
//        List<Orders> orders = restFulService.getOrdersList();
//        logger.info("返回结果->获取订单信息结束:[{}]", orders);
//        return ResponseDTO.returnSuccess("操作成功", orders);
//    }
//
//    @RequestMapping("/getOrdersListByUser")
//    @ResponseBody
//    public ResponseDTO getOrdersListByUser() {
//        logger.info("收到请求->获取订单信息");
//        List<Orders> orders = restFulService.getOrdersListByUser();
//        logger.info("返回结果->获取订单信息结束:[{}]", orders);
//        return ResponseDTO.returnSuccess("操作成功", orders);
//    }
//
//    @RequestMapping("/removeOrdersById/{id}")
//    @ResponseBody
//    public ResponseDTO removeOrdersById(@PathVariable String id) {
//        logger.info("收到请求->删除订单,id:[{}]", id);
//        restFulService.removeOrdersById(id);
//        logger.info("返回结果->删除订单结束");
//        return ResponseDTO.returnSuccess("操作成功");
//    }

}

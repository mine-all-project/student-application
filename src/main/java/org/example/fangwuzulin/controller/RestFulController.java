package org.example.fangwuzulin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.dto.ResponseDTO;
import org.example.fangwuzulin.entity.AudioFiles;
import org.example.fangwuzulin.entity.Houses;
import org.example.fangwuzulin.form.HousesForm;
import org.example.fangwuzulin.form.LeaveMessageForm;
import org.example.fangwuzulin.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    @RequestMapping("/getHousesList")
    public ResponseDTO getHousesList() {
        logger.info("收到请求->获取房源列表");
        List<Houses> houses = restFulService.getHousesList();
        logger.info("返回结果->获取房源列表完成:[{}]", houses);
        return ResponseDTO.returnSuccess("操作成功", houses);
    }

    @RequestMapping("/getHousesListByTitle")
    public ResponseDTO getHousesListByTitle(String title) {
        logger.info("收到请求->获取房源列表，title:[{}]", title);
        List<Houses> houses = restFulService.getHousesListByTitle(title);
        logger.info("返回结果->获取房源列表完成:[{}]", houses);
        return ResponseDTO.returnSuccess("操作成功", houses);
    }

    @RequestMapping("/getHousesById")
    public ResponseDTO getHousesById(String id) {
        logger.info("收到请求->获取房源数据,id:[{}]", id);
        Houses houses = restFulService.getHousesById(id);
        logger.info("返回结果->获取房源数据完成:[{}]", houses);
        return ResponseDTO.returnSuccess("操作成功", houses);
    }

    @DeleteMapping("/removeHousesById/{id}")
    @RequiresPermissions("login")
    public ResponseDTO removeHousesById(@PathVariable("id") String id) {
        logger.info("收到请求->删除房源,id:[{}]", id);
        restFulService.removeHousesById(id);
        logger.info("返回结果->删除房源完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/getHousesByUser")
    @RequiresPermissions("login")
    public ResponseDTO getHousesByUser() {
        logger.info("收到请求->获取我的房源列表");
        List<Houses> houses = restFulService.getHousesByUser();
        logger.info("返回结果->获取我的房源列表完成:[{}]", houses);
        return ResponseDTO.returnSuccess("操作成功", houses);
    }

    @RequestMapping("/saveHousesInfo")
    @RequiresPermissions("login")
    public ResponseDTO saveHousesInfo(@RequestBody HousesForm form) {
        logger.info("收到请求->保存房源信息:[{}]", form);
        super.validator(form, IsNotNull.class);
        restFulService.saveHousesInfo(form);
        logger.info("返回结果->保存房源信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

    @RequestMapping("/uploadFile")
    @RequiresPermissions("login")
    public ResponseDTO uploadFile(HttpServletRequest request) {
        logger.info("收到请求->上传文件");
        AudioFiles audioFiles = restFulService.uploadFile(request);
        logger.info("文件上传完成:[{}]", audioFiles);
        return ResponseDTO.returnSuccess("操作成功", audioFiles);
    }

    @RequestMapping("/saveLeaveMessage")
    @RequiresPermissions("login")
    public ResponseDTO saveLeaveMessage(@RequestBody LeaveMessageForm form) {
        logger.info("收到请求->保存评论信息:[{}]", form);
        super.validator(form, IsNotNull.class);
        restFulService.saveLeaveMessage(form);
        logger.info("返回结果->保存评论信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

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

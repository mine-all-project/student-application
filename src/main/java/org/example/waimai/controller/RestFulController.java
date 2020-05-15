package org.example.waimai.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.waimai.config.groups.IsNotNull;
import org.example.waimai.dto.ResponseDTO;
import org.example.waimai.entity.AudioFiles;
import org.example.waimai.entity.Contracts;
import org.example.waimai.entity.Houses;
import org.example.waimai.entity.LeaveMessage;
import org.example.waimai.form.ContractForm;
import org.example.waimai.form.HousesForm;
import org.example.waimai.form.LeaveMessageForm;
import org.example.waimai.service.RestFulService;
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

    @RequestMapping("/getContractsList")
    public ResponseDTO getContractsList() {
        logger.info("收到请求->获取合同列表");
        List<Contracts> contracts = restFulService.getContractsList();
        logger.info("返回结果->获取合同列表完成:[{}]", contracts);
        return ResponseDTO.returnSuccess("操作成功", contracts);
    }

    @RequestMapping("/getContractsById")
    public ResponseDTO getContractsById(String id) {
        logger.info("收到请求->获取合同数据,id:[{}]", id);
        Contracts contracts = restFulService.getContractsById(id);
        logger.info("返回结果->获取合同数据完成:[{}]", contracts);
        return ResponseDTO.returnSuccess("操作成功", contracts);
    }

    @DeleteMapping("/removeContractsById/{id}")
    @RequiresPermissions("manage")
    public ResponseDTO removeContractsById(@PathVariable("id") String id) {
        logger.info("收到请求->删除合同,id:[{}]", id);
        restFulService.removeContractsById(id);
        logger.info("返回结果->删除合同完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/saveContractInfo")
    @RequiresPermissions("manage")
    public ResponseDTO saveContractInfo(@RequestBody ContractForm form) {
        logger.info("收到请求->保存合同信息:[{}]", form);
        super.validator(form, IsNotNull.class);
        restFulService.saveContractInfo(form);
        logger.info("返回结果->保存合同信息完成:[{}]", form);
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
    public ResponseDTO saveLeaveMessage(@RequestBody LeaveMessageForm form) {
        logger.info("收到请求->保存评论信息:[{}]", form);
        super.validator(form, IsNotNull.class);
        restFulService.saveLeaveMessage(form);
        logger.info("返回结果->保存评论信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

    @RequestMapping("/getLeaveMessage")
    @RequiresPermissions("manage")
    public ResponseDTO getLeaveMessage() {
        logger.info("收到请求->获取评论信息");
        List<LeaveMessage> messages = restFulService.getLeaveMessage();
        logger.info("返回结果->获取评论信息完成:[{}]", messages);
        return ResponseDTO.returnSuccess("操作成功", messages);
    }

    @RequestMapping("/removeLeaveMessage/{id}")
    @RequiresPermissions("manage")
    public ResponseDTO removeLeaveMessage(@PathVariable String id) {
        logger.info("收到请求->删除评论信息");
        restFulService.removeLeaveMessage(id);
        logger.info("返回结果->删除评论信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

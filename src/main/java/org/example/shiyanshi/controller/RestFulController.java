package org.example.shiyanshi.controller;

import org.example.shiyanshi.config.base.BaseController;
import org.example.shiyanshi.config.groups.IsAdd;
import org.example.shiyanshi.config.groups.IsNotNull;
import org.example.shiyanshi.config.base.ResponseDTO;
import org.example.shiyanshi.entity.LineUps;
import org.example.shiyanshi.entity.Machines;
import org.example.shiyanshi.entity.Rooms;
import org.example.shiyanshi.form.LineUpsForm;
import org.example.shiyanshi.form.MachinesForm;
import org.example.shiyanshi.form.RoomsForm;
import org.example.shiyanshi.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RestFulController.class);

    private final RestFulService restFulService;

    public RestFulController(RestFulService restFulService) {
        this.restFulService = restFulService;
    }

    @RequestMapping("/getLineUpsListByUser")
    public ResponseDTO getLineUpsList() {
        logger.info("收到请求->获取我的预约列表");
        List<LineUps> list = restFulService.getLineUpsListByUser();
        logger.info("返回结果-获取我的预约列表完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @RequestMapping("/saveLineUps")
    public ResponseDTO saveLineUps(@RequestBody LineUpsForm form) {
        validator(form, IsAdd.class);
        logger.info("收到请求->保存预约信息:[{}]", form);
        restFulService.saveLineUps(form);
        logger.info("返回结果->保存预约信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/closeLineUpsById")
    public ResponseDTO closeLineUpsById(String id) {
        logger.info("收到请求->关闭预约,id:[{}]", id);
        restFulService.closeLineUpsById(id);
        logger.info("返回结果->关闭预约完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/startLineUpsById")
    public ResponseDTO startLineUpsById(String id) {
        logger.info("收到请求->开始使用,id:[{}]", id);
        restFulService.startLineUpsById(id);
        logger.info("返回结果->开始使用完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/endLineUpsById")
    public ResponseDTO endLineUpsById(String id) {
        logger.info("收到请求->结束使用,id:[{}]", id);
        restFulService.endLineUpsById(id);
        logger.info("返回结果->结束使用完成");
        return ResponseDTO.returnSuccess("数据更新成功");
    }

    @RequestMapping("/getRoomsList")
    public ResponseDTO getRoomsList() {
        logger.info("收到请求->获取实验室列表");
        List<Rooms> list = restFulService.getRoomsList();
        logger.info("返回结果->获取实验室列表完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @RequestMapping("/saveRoomsInfo")
    public ResponseDTO saveRoomsInfo(@RequestBody RoomsForm form) {
        logger.info("收到请求->保存实验室信息:[{}]", form);
        validator(form, IsNotNull.class);
        restFulService.saveRoomsInfo(form);
        logger.info("返回结果->保存实验室信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

    @RequestMapping("/delRoomsById")
    public ResponseDTO delRoomsById(String id) {
        logger.info("收到请求->删除实训室信息,id:[{}]", id);
        restFulService.delRoomsById(id);
        logger.info("返回结果->删除实训室信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/getRoomsById")
    public ResponseDTO getRoomsById(String id) {
        logger.info("收到请求->获取实训室数据,id:[{}]", id);
        Rooms data = restFulService.getRoomsById(id);
        logger.info("返回结果->获取实训室数据完成:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/getMachinesById")
    public ResponseDTO getMachinesById(String id) {
        logger.info("收到请求->获取设备数据,id:[{}]", id);
        Machines data = restFulService.getMachinesById(id);
        logger.info("返回结果->获取设备数据完成:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/getMachinesList")
    public ResponseDTO getMachinesList() {
        logger.info("收到请求->获取设备列表");
        List<Machines> list = restFulService.getMachinesList();
        logger.info("返回结果->获取设备列表完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @RequestMapping("/saveMachinesInfo")
    public ResponseDTO saveMachinesInfo(@RequestBody MachinesForm form) {
        logger.info("收到请求->保存设备信息:[{}]", form);
        restFulService.saveMachinesInfo(form);
        logger.info("返回结果->保存设备信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/delMachinesById")
    public ResponseDTO delMachinesById(String id) {
        logger.info("收到请求->删除设备信息,id:[{}]", id);
        restFulService.delMachinesById(id);
        logger.info("返回结果->删除设备信息完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

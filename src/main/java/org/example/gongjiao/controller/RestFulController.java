package org.example.gongjiao.controller;

import org.example.gongjiao.dto.ResponseDTO;
import org.example.gongjiao.entity.Linees;
import org.example.gongjiao.entity.Papers;
import org.example.gongjiao.entity.Stands;
import org.example.gongjiao.form.LinesForm;
import org.example.gongjiao.form.PapersForm;
import org.example.gongjiao.form.StandsForm;
import org.example.gongjiao.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RestFulController.class);

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
    public ResponseDTO saveLinesInfo(@RequestBody LinesForm form) {
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

    @GetMapping("/getStandsById")
    public ResponseDTO getStandsById(String id) {
        logger.info("收到请求->获取站点数据,id:[{}]", id);
        Stands stands = restFulService.getStandsById(id);
        logger.info("返回结果->获取站点数据完成:[{}]", stands);
        return ResponseDTO.returnSuccess("操作成功", stands);
    }

    @RequestMapping("/saveStandsInfo")
    @ResponseBody
    public ResponseDTO saveStandsInfo(@RequestBody StandsForm form) {
        logger.info("收到请求->保存站点信息:[{}]", form);
        restFulService.saveStandsInfo(form);
        logger.info("返回结果->保存站点信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

    @RequestMapping("/searchLinesByNumber")
    @ResponseBody
    public ResponseDTO searchLinesByNumber(@NotBlank String name) {
        logger.info("收到请求->搜索线路,name:[{}]", name);
        List<Linees> lines = restFulService.searchLinesByNumber(name);
        logger.info("返回结果->搜索线路完成:[{}]", lines);
        return ResponseDTO.returnSuccess("操作成功", lines);
    }

    @RequestMapping("/searchLinesByStands")
    @ResponseBody
    public ResponseDTO searchLinesByStands(@NotBlank String name) {
        logger.info("收到请求->搜索站台,name:[{}]", name);
        List<Linees> linees = restFulService.searchLinesByStands(name);
        logger.info("返回结果->搜索站台完成:[{}]", linees);
        return ResponseDTO.returnSuccess("操作成功", linees);
    }

    @DeleteMapping("/removeStandsById/{id}")
    public ResponseDTO removeStandsById(@PathVariable("id") String id) {
        logger.info("收到请求->删除线路,id:[{}]", id);
        restFulService.removeStandsById(id);
        logger.info("返回结果->删除线路完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/getPapersByKeyWords/{keyWords}")
    public ResponseDTO getPapersByKeyWords(@PathVariable String keyWords) {
        logger.info("收到请求->获取文章列表,keyWords:[{}]", keyWords);
        List<Papers> papers = restFulService.getPapersByKeyWords(keyWords);
        logger.info("返回结果->获取文章列表完成:[{}]", papers);
        return ResponseDTO.returnSuccess("操作成功", papers);
    }

    @GetMapping("/getMinePapersByKeyWords/{keyWords}")
    public ResponseDTO getMinePapersByKeyWords(@PathVariable String keyWords) {
        logger.info("收到请求->获取文章列表,keyWords:[{}]", keyWords);
        List<Papers> papers = restFulService.getMinePapersByKeyWords(keyWords);
        logger.info("返回结果->获取文章列表完成:[{}]", papers);
        return ResponseDTO.returnSuccess("操作成功", papers);
    }

    @GetMapping("/getPapersById")
    public ResponseDTO getPapersById(String id) {
        logger.info("收到请求->获取文章信息,id:[{}]", id);
        Papers papers = restFulService.getPapersById(id);
        logger.info("返回结果->获取文章信息完成:[{}]", papers);
        return ResponseDTO.returnSuccess("操作成功", papers);
    }

    @PostMapping("/savePapers")
    public ResponseDTO savePapers(@RequestBody PapersForm form) {
        logger.info("收到请求->保存文章数据:[{}]", form);
        restFulService.savePapers(form);
        logger.info("返回结果->保存文章数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @DeleteMapping("/removePapersById/{id}")
    public ResponseDTO removePapersById(@PathVariable("id") String id) {
        logger.info("收到请求->删除文章数据,id:[{}]", id);
        restFulService.removePapersById(id);
        logger.info("返回结果->删除文章数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

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

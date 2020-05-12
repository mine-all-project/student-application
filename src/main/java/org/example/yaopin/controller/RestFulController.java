package org.example.yaopin.controller;

import org.example.yaopin.config.groups.IsAdd;
import org.example.yaopin.config.groups.IsEdit;
import org.example.yaopin.config.groups.IsNotNull;
import org.example.yaopin.dto.ResponseDTO;
import org.example.yaopin.entity.Goods;
import org.example.yaopin.entity.Messages;
import org.example.yaopin.entity.Purchases;
import org.example.yaopin.form.MessagesForm;
import org.example.yaopin.form.PurchasesForm;
import org.example.yaopin.service.RestFulService;
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

    @RequestMapping("/getPurchasesList")
    @ResponseBody
    public ResponseDTO getPurchasesList() {
        logger.info("收到请求->获取采购列表");
        List<Purchases> list = restFulService.getPurchasesList();
        logger.info("返回结果->获取采购列表完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @GetMapping("/getPurchasesById")
    public ResponseDTO getPurchasesById(String id) {
        logger.info("收到请求->获取采购数据,id:[{}]", id);
        Purchases data = restFulService.getPurchasesById(id);
        logger.info("返回结果->获取采购数据完成:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }

    @RequestMapping("/savePurchasesInfo")
    public ResponseDTO savePurchasesInfo(@RequestBody PurchasesForm form) {
        logger.info("收到请求->保存采购信息:[{}]", form);
        validator(form, IsAdd.class, IsEdit.class);
        restFulService.savePurchasesInfo(form);
        logger.info("返回结果->保存采购信息完成:[{}]", form);
        return ResponseDTO.returnSuccess("操作成功", form);
    }

//    @RequestMapping("/saveStorageInfo")
//    @ResponseBody
//    public ResponseDTO saveStorageInfo(@RequestBody StorageForm form) {
//        logger.info("收到请求->保存入库信息:[{}]", form);
//        restFulService.saveStorageInfo(form);
//        logger.info("返回结果->保存入库信息完成:[{}]", form);
//        return ResponseDTO.returnSuccess("操作成功", form);
//    }

    @DeleteMapping("/flagDelPurchasesById")
    public ResponseDTO flagDelPurchasesById(String id) {
        logger.info("收到请求->采购退货,id:[{}]", id);
        restFulService.flagDelPurchasesById(id);
        logger.info("返回结果->采购退货完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/getGoodsListByFlag")
    public ResponseDTO getGoodsListByFlag() {
        logger.info("收到请求->获取商品列表");
        List<Goods> list = restFulService.getGoodsListByFlag();
        logger.info("返回结果->获取商品列表:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @RequestMapping("/saveMessages")
    public ResponseDTO saveMessages(@RequestBody MessagesForm form) {
        logger.info("收到请求->保存消息数据:[{}]", form);
        validator(form, IsAdd.class, IsEdit.class);
        restFulService.saveMessages(form);
        logger.info("返回结果->保存消息数据完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @RequestMapping("/getMessageList")
    public ResponseDTO getMessageList() {
        logger.info("收到请求->获取消息列表");
        List<Messages> list = restFulService.getMessageList();
        logger.info("返回结果->获取消息列表完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }

    @RequestMapping("/getMessagesById")
    public ResponseDTO getMessagesById(String id) {
        logger.info("收到请求->获取消息详情");
        Messages data = restFulService.getMessagesById(id);
        logger.info("返回结果->获取获取消息详情完成:[{}]", data);
        return ResponseDTO.returnSuccess("操作成功", data);
    }


//
//    @RequestMapping("/getStandsList")
//    @ResponseBody
//    public ResponseDTO getStandsList() {
//        logger.info("收到请求->获取站点列表]");
//        List<Stands> stands = restFulService.getStandsList();
//        logger.info("返回结果->获取站点列表完成:[{}]", stands);
//        return ResponseDTO.returnSuccess("操作成功", stands);
//    }
//
//    @GetMapping("/getStandsById")
//    public ResponseDTO getStandsById(String id) {
//        logger.info("收到请求->获取站点数据,id:[{}]", id);
//        Stands stands = restFulService.getStandsById(id);
//        logger.info("返回结果->获取站点数据完成:[{}]", stands);
//        return ResponseDTO.returnSuccess("操作成功", stands);
//    }
//
//    @RequestMapping("/saveStandsInfo")
//    @ResponseBody
//    public ResponseDTO saveStandsInfo(@RequestBody StandsForm form) {
//        logger.info("收到请求->保存站点信息:[{}]", form);
//        restFulService.saveStandsInfo(form);
//        logger.info("返回结果->保存站点信息完成:[{}]", form);
//        return ResponseDTO.returnSuccess("操作成功", form);
//    }
//
//    @RequestMapping("/searchLinesByNumber")
//    @ResponseBody
//    public ResponseDTO searchLinesByNumber(@NotBlank String name) {
//        logger.info("收到请求->搜索线路,name:[{}]", name);
//        List<Linees> lines = restFulService.searchLinesByNumber(name);
//        logger.info("返回结果->搜索线路完成:[{}]", lines);
//        return ResponseDTO.returnSuccess("操作成功", lines);
//    }
//
//    @RequestMapping("/searchLinesByStands")
//    @ResponseBody
//    public ResponseDTO searchLinesByStands(@NotBlank String name) {
//        logger.info("收到请求->搜索站台,name:[{}]", name);
//        List<Linees> linees = restFulService.searchLinesByStands(name);
//        logger.info("返回结果->搜索站台完成:[{}]", linees);
//        return ResponseDTO.returnSuccess("操作成功", linees);
//    }
//
//    @DeleteMapping("/removeStandsById/{id}")
//    public ResponseDTO removeStandsById(@PathVariable("id") String id) {
//        logger.info("收到请求->删除线路,id:[{}]", id);
//        restFulService.removeStandsById(id);
//        logger.info("返回结果->删除线路完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @GetMapping("/getPapersByKeyWords/{keyWords}")
//    public ResponseDTO getPapersByKeyWords(@PathVariable String keyWords) {
//        logger.info("收到请求->获取文章列表,keyWords:[{}]", keyWords);
//        List<Papers> papers = restFulService.getPapersByKeyWords(keyWords);
//        logger.info("返回结果->获取文章列表完成:[{}]", papers);
//        return ResponseDTO.returnSuccess("操作成功", papers);
//    }
//
//    @GetMapping("/getMinePapersByKeyWords/{keyWords}")
//    public ResponseDTO getMinePapersByKeyWords(@PathVariable String keyWords) {
//        logger.info("收到请求->获取文章列表,keyWords:[{}]", keyWords);
//        List<Papers> papers = restFulService.getMinePapersByKeyWords(keyWords);
//        logger.info("返回结果->获取文章列表完成:[{}]", papers);
//        return ResponseDTO.returnSuccess("操作成功", papers);
//    }
//
//    @GetMapping("/getPapersById")
//    public ResponseDTO getPapersById(String id) {
//        logger.info("收到请求->获取文章信息,id:[{}]", id);
//        Papers papers = restFulService.getPapersById(id);
//        logger.info("返回结果->获取文章信息完成:[{}]", papers);
//        return ResponseDTO.returnSuccess("操作成功", papers);
//    }
//
//    @PostMapping("/savePapers")
//    public ResponseDTO savePapers(@RequestBody PapersForm form) {
//        logger.info("收到请求->保存文章数据:[{}]", form);
//        restFulService.savePapers(form);
//        logger.info("返回结果->保存文章数据完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }
//
//    @DeleteMapping("/removePapersById/{id}")
//    public ResponseDTO removePapersById(@PathVariable("id") String id) {
//        logger.info("收到请求->删除文章数据,id:[{}]", id);
//        restFulService.removePapersById(id);
//        logger.info("返回结果->删除文章数据完成");
//        return ResponseDTO.returnSuccess("操作成功");
//    }

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

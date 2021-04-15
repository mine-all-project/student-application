package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.custom.entity.RequestPull;
import cn.crabapples.application.custom.service.RequestPullService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分享请求接口
 */
@RestController
@Slf4j
@RequestMapping("/api/request-pull")
public class RequestPullController extends BaseController {

    private final RequestPullService requestPullService;

    public RequestPullController(
            RequestPullService requestPullService) {
        this.requestPullService = requestPullService;
    }


    @GetMapping("/save/{id}")
    public ResponseDTO requestPull(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->发起浏览申请,id:[{}]", id);
        requestPullService.requestPull(request, id);
        log.info("返回结果->发起浏览申请完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list")
    public ResponseDTO requestPullList() {
        log.info("收到请求->获取申请列表");
        List<RequestPull> list = requestPullService.requestPullList();
        log.info("返回结果->获取申请列表申请完成:[{}]", list);
        return ResponseDTO.returnSuccess("操作成功", list);
    }


    @GetMapping("/accept/{id}")
    public ResponseDTO accept(@PathVariable String id) {
        log.info("收到请求->通过审核");
        requestPullService.accept(id);
        log.info("返回结果->通过审核完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/unaccepted/{id}")
    public ResponseDTO unaccepted(@PathVariable String id) {
        log.info("收到请求->拒绝审核");
        requestPullService.unaccepted(id);
        log.info("返回结果->拒绝审核完成");
        return ResponseDTO.returnSuccess("操作成功");
    }
}

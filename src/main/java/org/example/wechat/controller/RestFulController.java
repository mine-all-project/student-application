package org.example.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.wechat.dto.ResponseDTO;
import org.example.wechat.from.RequestFrom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RestFulController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RestFulController.class);
    private final RestTemplate restTemplate;
    private static final String URL = "http://127.0.0.1:8073/send";

    public RestFulController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @RequestMapping("/pullMessage")
    @ResponseBody
    public ResponseDTO pullMessage(RequestFrom from) {
        System.err.println("收到消息");
        System.err.println(from);
        sendMessage();
        return null;
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public ResponseDTO sendMessage() {
        System.err.println("发送消息");
        Map<String,Object> param = new HashMap<>();
        param.put("type",100);
        param.put("msg","demo message");
        param.put("to_wxid","wxid_x3za57u8jmp12");
        param.put("robot_wxid","wxid_x3za57u8jmp12");
        String result = restTemplate.postForObject(URL, JSONObject.toJSONString(param), String.class);
        System.err.println(result);
        return null;
    }


}

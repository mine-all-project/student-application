package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.custom.entity.Message;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/message")
public class MessageController extends BaseController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get/paperId/{id}")
    public ResponseDTO getMessageByPaperId(@PathVariable String id) {
        log.info("收到请求->获取评论列表,id:[{}]", id);
        List<Message> list = messageService.getMessageByPaperId(id);
        log.info("返回结果->获取评论列表结束,[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/add")
    public ResponseDTO addMessage(HttpServletRequest request, @RequestBody Message message) {
        log.info("收到请求->添加评论:[{}]", message);
        messageService.addMessage(request, message);
        log.info("返回结果->添加评论结束");
        return ResponseDTO.returnSuccess();
    }
}

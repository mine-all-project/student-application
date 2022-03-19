package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Message;
import org.example.application.custom.form.MessageForm;
import org.example.application.custom.service.MessageService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/message")
public class MessageController extends BaseController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody MessageForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->发布评论:[{}]", form);
        Message entity = messageService.save(request, form);
        log.info("返回结果->发布评论结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }
}

package org.example.application.system.controller;

import org.example.application.common.BaseController;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.system.dto.ResponseDTO;
import org.example.application.system.service.FileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/test")
public class TestController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final FileInfoService fileInfoService;

    public TestController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @JwtIgnore
    @PostMapping("/form/post")
    public ResponseDTO formPost(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String againPassword = request.getParameter("againPassword");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String description = request.getParameter("description");
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("password", password);
        result.put("againPassword", againPassword);
        result.put("gender", gender);
        result.put("age", age);
        result.put("description", description);
        logger.info("收到请求->form表单post测试:\n" +
                        "name[{}],password:[{}],againPassword:[{}],gender:[{}],age:[{}],description:[{}]",
                name, password, againPassword, gender, age, description);
        logger.info("返回结果->form表单post测试完成");
        return ResponseDTO.returnSuccess("OK", result);
    }

}

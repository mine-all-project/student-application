package org.example.application.custom.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.BaseController;
import org.example.application.common.groups.IsNotNull;
import org.example.application.common.utils.jwt.JwtIgnore;
import org.example.application.custom.entity.Person;
import org.example.application.custom.form.PersonForm;
import org.example.application.custom.service.PersonService;
import org.example.application.system.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/person/")
public class PersonController extends BaseController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/list")
    public ResponseDTO getList(HttpServletRequest request) {
        log.info("收到请求->获取人员列表");
        List<Person> list = personService.getAll(request);
        log.info("返回结果->获取人员列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @GetMapping("/search/{keywords}")
    public ResponseDTO search(HttpServletRequest request, @PathVariable String keywords) {
        log.info("收到请求->搜索人员列表,keywords:[{}]", keywords);
        List<Person> list = personService.search(request,keywords);
        log.info("返回结果->搜索人员列表结束:[{}]", list);
        return ResponseDTO.returnSuccess(list);
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody PersonForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->保存人员:[{}]", form);
        Person entity = personService.save(request, form);
        log.info("返回结果->保存人员结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @JwtIgnore
    @PostMapping("/registry")
    public ResponseDTO registry(HttpServletRequest request, @RequestBody PersonForm form) {
        validator(form, IsNotNull.class);
        log.info("收到请求->注册人员:[{}]", form);
        Person entity = personService.save(request, form);
        log.info("返回结果->注册人员结束:[{}]", entity);
        return ResponseDTO.returnSuccess(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(HttpServletRequest request, @PathVariable String id) {
        log.info("收到请求->删除人员,id:[{}]", id);
        personService.deleteById(request,id);
        log.info("返回结果->删除的人员结束");
        return ResponseDTO.returnSuccess();
    }
}

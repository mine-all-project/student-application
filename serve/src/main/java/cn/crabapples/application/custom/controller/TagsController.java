package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import cn.crabapples.application.custom.service.TagsService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签接口
 */
@RestController
@Slf4j
@RequestMapping("/api/tags")
public class TagsController extends BaseController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @PostMapping("/save")
    public ResponseDTO save(@RequestBody TagsForm form) {
        log.info("收到请求->保存标签:[{}]", form);
        super.validator(form, IsLogin.class);
        tagsService.saveTags(form);
        log.info("返回结果->保存标签完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Tags> resultList = tagsService.getAll();
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/removeById/{id}")
    public ResponseDTO removeById(@PathVariable("id") String id) {
        log.info("收到请求->删除标签");
        tagsService.removeById(id);
        log.info("返回结果->删除标签完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

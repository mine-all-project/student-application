package cn.crabapples.application.custom.controller;

import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import cn.crabapples.application.custom.service.TagsService;
import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.common.utils.jwt.JwtIgnore;
import cn.crabapples.application.system.dto.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@RestController
@Api("系统管理")
@Slf4j
@RequestMapping("/api/tags")
public class TagsController extends BaseController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @JwtIgnore
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存课题接口")
    public ResponseDTO save(@RequestBody TagsForm form) {
        log.info("收到请求->保存课题:[{}]", form);
        super.validator(form, IsLogin.class);
        tagsService.saveTags(form);
        log.info("返回结果->保存课题完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @JwtIgnore
    @GetMapping("/list")
    @ApiOperation(value = "获取列表", notes = "获取课题列表接口")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Tags> resultList = tagsService.getAll();
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

}

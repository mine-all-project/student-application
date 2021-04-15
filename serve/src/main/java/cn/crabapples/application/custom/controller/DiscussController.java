package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.form.DiscussForm;
import cn.crabapples.application.custom.service.DiscussService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论接口
 */
@RestController
@Slf4j
@RequestMapping("/api/discuss")
public class DiscussController extends BaseController {

    private final DiscussService discussService;

    public DiscussController(DiscussService discussService) {
        this.discussService = discussService;
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody DiscussForm form) {
        log.info("收到请求->保存评论:[{}]", form);
        super.validator(form, IsLogin.class);
        discussService.saveDiscuss(request, form);
        log.info("返回结果->保存评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list/{subjectId}")
    public ResponseDTO list(@PathVariable String subjectId) {
        log.info("收到请求->获取列表");
        List<Discuss> resultList = discussService.getAll(subjectId);
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/removeById/{id}")
    public ResponseDTO removeById(@PathVariable("id") String id) {
        log.info("收到请求->删除评论");
        discussService.removeById(id);
        log.info("返回结果->删除评论完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

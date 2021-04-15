package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.custom.entity.Papers1;
import cn.crabapples.application.custom.form.PapersForm1;
import cn.crabapples.application.custom.service.PapersService1;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章接口(mysql)
 */
@RestController
@Slf4j
@RequestMapping("/api/paper1")
public class Paper1Controller extends BaseController {

    private final PapersService1 papersService;

    public Paper1Controller(PapersService1 papersService) {
        this.papersService = papersService;
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody PapersForm1 form) {
        log.info("收到请求->保存文章:[{}]", form);
        super.validator(form, IsLogin.class);
        papersService.savePapers(request,form);
        log.info("返回结果->保存标签完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Papers1> resultList = papersService.getAll();
        log.info("返回结果->获取列表完成:[{}]", resultList);
        return ResponseDTO.returnSuccess("操作成功", resultList);
    }

    @GetMapping("/removeById/{id}")
    public ResponseDTO removeById(@PathVariable("id") String id) {
        log.info("收到请求->删除文章");
        papersService.removeById(id);
        log.info("返回结果->删除文章完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

}

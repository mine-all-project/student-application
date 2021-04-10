package cn.crabapples.application.custom.controller;

import cn.crabapples.application.common.BaseController;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.custom.entity.Papers;
import cn.crabapples.application.custom.form.PapersForm;
import cn.crabapples.application.custom.service.PapersService;
import cn.crabapples.application.system.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 14:39
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@RestController
@Slf4j
@RequestMapping("/api/paper")
public class PaperController extends BaseController {

    private final PapersService papersService;

    public PaperController(PapersService papersService) {
        this.papersService = papersService;
    }

    @PostMapping("/save")
    public ResponseDTO save(HttpServletRequest request, @RequestBody PapersForm form) {
        log.info("收到请求->保存文章:[{}]", form);
        super.validator(form, IsLogin.class);
        papersService.savePapers(request,form);
        log.info("返回结果->保存标签完成");
        return ResponseDTO.returnSuccess("操作成功");
    }

    @GetMapping("/list")
    public ResponseDTO list() {
        log.info("收到请求->获取列表");
        List<Papers> resultList = papersService.getAll();
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

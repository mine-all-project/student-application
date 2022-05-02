package com.controller;

import com.annotation.IgnoreAuth;
import com.entity.Jingxiaodian;
import com.service.JingxiaodianService;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/jingxiaodian")
public class JingxiaodianController {
    private final Logger log = LoggerFactory.getLogger(JingxiaodianController.class);
    private final JingxiaodianService jingxiaodianService;

    public JingxiaodianController(JingxiaodianService jingxiaodianService) {
        this.jingxiaodianService = jingxiaodianService;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R list(@RequestBody Jingxiaodian entity,
                  HttpServletRequest request) {
        log.info("保存经销店:[{}]", entity);
        jingxiaodianService.save(entity);
        return R.ok();
    }

    /**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/flist")
    public R flist(HttpServletRequest request) {
        List<Jingxiaodian> list = jingxiaodianService.getList();
        return R.ok().put("data", list);
    }

    /**
     * 删除
     */
    @IgnoreAuth
    @RequestMapping("/delete")
    public R delete(HttpServletRequest request,@RequestBody List<String> ids) {
        jingxiaodianService.delete(ids);
        return R.ok();
    }

    /**
     * 删除
     */
    @IgnoreAuth
    @RequestMapping("/info/{id}")
    public R info(HttpServletRequest request, @PathVariable String id) {
        Jingxiaodian entity = jingxiaodianService.info(id);
        return R.ok().put("data",entity);
    }
}

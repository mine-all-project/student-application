package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ForumEntity;
import com.entity.Jingxiaodian;
import com.entity.Yuyue;
import com.service.JingxiaodianService;
import com.service.YuyueService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/yuyue")
public class YuyueController {
    private final Logger log = LoggerFactory.getLogger(YuyueController.class);
    private final YuyueService yuyueService;

    public YuyueController(YuyueService yuyueService) {
        this.yuyueService = yuyueService;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R list(@RequestBody Yuyue entity,
                  HttpServletRequest request) {
        log.info("保存预约:[{}]", entity);
        yuyueService.save(entity);
        return R.ok();
    }

    /**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/flist/{type}")
    public R flist(HttpServletRequest request, @PathVariable String type) {
        List<Yuyue> list = yuyueService.getList(type);
        return R.ok().put("data", list);
    }

    /**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/finish/{id}")
    public R finish(HttpServletRequest request,  @PathVariable String id) {
        yuyueService.finish(id);
        return R.ok();
    }

    /**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/finish/{id}/{storeid}")
    public R finish(HttpServletRequest request,  @PathVariable String id,  @PathVariable String storeid) {
        yuyueService.finish(id,storeid);
        return R.ok();
    }
//
//    /**
//     * 删除
//     */
//    @IgnoreAuth
//    @RequestMapping("/delete")
//    public R delete(HttpServletRequest request,@RequestBody List<String> ids) {
//        jingxiaodianService.delete(ids);
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @IgnoreAuth
//    @RequestMapping("/info/{id}")
//    public R info(HttpServletRequest request, @PathVariable String id) {
//        Jingxiaodian entity = jingxiaodianService.info(id);
//        return R.ok().put("data",entity);
//    }
}

package com.xbb.cases.controller.sys;

import com.xbb.cases.mapper.CasesMapper;
import com.xbb.cases.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys")
public class SysIndexController {

    private String prefix = "/sys/";

    @Resource
    private CasesMapper casesMapper;

    // 后台首页
    @RequestMapping("/index.html")
    public String sysIndexHtml () {
        return prefix + "index";
    }

    @RequestMapping("/home.html")
    public String sysHomeHtml (Model model) {
        int count1 = casesMapper.selectCountByPaging(null, null, null, null, 0, null);
        int count2 = casesMapper.selectCountByPaging(null, null, null, null, 1, null);
        int count3 = casesMapper.selectCountByPaging(null, null, null, null, 2, null);
        int count4 = casesMapper.selectCountByPaging(null, null, null, null, 3, null);
        model.addAttribute("count1", count1);
        model.addAttribute("count2", count2);
        model.addAttribute("count3", count3);
        model.addAttribute("count4", count4);
        return prefix + "home";
    }

}

package com.xbb.cases.controller.nurse;

import com.xbb.cases.mapper.CasesMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/nurse")
public class NurseIndexController {

    private String prefix = "/nurse/";

    @Resource
    private CasesMapper casesMapper;

    // 护士首页
    @RequestMapping("/index.html")
    public String nurseIndexHtml () {
        return prefix + "index";
    }

    @RequestMapping("/home.html")
    public String nurseHomeHtml (Model model) {
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

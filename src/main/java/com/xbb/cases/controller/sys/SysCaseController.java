package com.xbb.cases.controller.sys;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.Cases;
import com.xbb.cases.entity.Cost;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.CasesMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/case")
public class SysCaseController {

    private String prefix = "/sys/case/";

    @Resource
    private CasesMapper casesMapper;

    // 医嘱管理
    @RequestMapping("/advice.html")
    public String adviceHtml() {
        return prefix + "advice";
    }

    // 病例管理
    @RequestMapping("/case.html")
    public String caseHtml() {
        return prefix + "case";
    }

    // 检查报告管理
    @RequestMapping("/inspect.html")
    public String inspectHtml() {
        return prefix + "inspect";
    }

    // 护理文书管理
    @RequestMapping("/nursing.html")
    public String nursingHtml() {
        return prefix + "nursing";
    }

    // 分页数据
    @ResponseBody
    @RequestMapping("/data")
    public RespResult data(@RequestParam("page") int page,
                           @RequestParam("limit") int limit,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value = "cellphone", required = false, defaultValue = "") String cellphone,
                           @RequestParam(value = "identityCard", required = false, defaultValue = "") String identityCard,
                           @RequestParam(value = "title", required = false, defaultValue = "") String title,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "casesType", required = false) Integer casesType) {
        if (name.equals("")) name = null;
        if (cellphone.equals("")) cellphone = null;
        if (identityCard.equals("")) identityCard = null;
        if (title.equals("")) title = null;
        List<Cases> casesList = casesMapper.selectListByPaging(
                (page - 1) * limit,
                limit,
                name,
                cellphone,
                identityCard,
                title,
                status,
                casesType
        );
        int count = casesMapper.selectCountByPaging(
                name,
                cellphone,
                identityCard,
                title,
                status,
                casesType
        );

        // 判断是否需要进行匿名操作
        for (Cases cases : casesList) {
            if (cases.getStatus() == 1) {
                User user = cases.getUser();
                user.setName(user.getName().substring(0, 1) + "**");
                user.setCellphone(user.getCellphone().substring(0, 3) + "****" + user.getCellphone().substring(7, 11));
                user.setIdentityCard(user.getIdentityCard().substring(0, 6) + "******" + user.getIdentityCard().substring(12, 18));
                String s = "";
                int leng = user.getAddress().length() / 2;
                for (int i = 0; i < leng; i++) {
                    s = s + "*";
                }
                user.setAddress(user.getAddress().substring(0, leng) + s);
            }
        }

        RespResult respResult = new RespResult();
        respResult.success(casesList, count);
        return respResult;
    }

    // 医嘱详情
    @RequestMapping("/advice/item.html")
    public String itemAdviceHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (cases.getStatus() == 1) {
            User user = cases.getUser();
            user.setName(user.getName().substring(0, 1) + "**");
            user.setCellphone(user.getCellphone().substring(0, 3) + "****" + user.getCellphone().substring(7, 11));
            user.setIdentityCard(user.getIdentityCard().substring(0, 6) + "******" + user.getIdentityCard().substring(12, 18));
            String s = "";
            int leng = user.getAddress().length() / 2;
            for (int i = 0; i < leng; i++) {
                s = s + "*";
            }
            user.setAddress(user.getAddress().substring(0, leng) + s);
        }
        model.addAttribute("cases", cases);
        return prefix + "advice_item";
    }

    // 病例详情
    @RequestMapping("/case/item.html")
    public String itemCaseHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (cases.getStatus() == 1) {
            User user = cases.getUser();
            user.setName(user.getName().substring(0, 1) + "**");
            user.setCellphone(user.getCellphone().substring(0, 3) + "****" + user.getCellphone().substring(7, 11));
            user.setIdentityCard(user.getIdentityCard().substring(0, 6) + "******" + user.getIdentityCard().substring(12, 18));
            String s = "";
            int leng = user.getAddress().length() / 2;
            for (int i = 0; i < leng; i++) {
                s = s + "*";
            }
            user.setAddress(user.getAddress().substring(0, leng) + s);
        }
        model.addAttribute("cases", cases);
        return prefix + "case_item";
    }

    // 检查报告详情
    @RequestMapping("/inspect/item.html")
    public String itemInspectHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (cases.getStatus() == 1) {
            User user = cases.getUser();
            user.setName(user.getName().substring(0, 1) + "**");
            user.setCellphone(user.getCellphone().substring(0, 3) + "****" + user.getCellphone().substring(7, 11));
            user.setIdentityCard(user.getIdentityCard().substring(0, 6) + "******" + user.getIdentityCard().substring(12, 18));
            String s = "";
            int leng = user.getAddress().length() / 2;
            for (int i = 0; i < leng; i++) {
                s = s + "*";
            }
            user.setAddress(user.getAddress().substring(0, leng) + s);
        }
        model.addAttribute("cases", cases);
        return prefix + "inspect_item";
    }

    // 护理文书详情
    @RequestMapping("/nursing/item.html")
    public String itemNursingHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (cases.getStatus() == 1) {
            User user = cases.getUser();
            user.setName(user.getName().substring(0, 1) + "**");
            user.setCellphone(user.getCellphone().substring(0, 3) + "****" + user.getCellphone().substring(7, 11));
            user.setIdentityCard(user.getIdentityCard().substring(0, 6) + "******" + user.getIdentityCard().substring(12, 18));
            String s = "";
            int leng = user.getAddress().length() / 2;
            for (int i = 0; i < leng; i++) {
                s = s + "*";
            }
            user.setAddress(user.getAddress().substring(0, leng) + s);
        }
        model.addAttribute("cases", cases);
        return prefix + "nursing_item";
    }

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody int id) {
        casesMapper.deleteByPrimaryKey(id);
        return new RespResult();
    }

    public static void main(String[] args) {

        System.out.println("360521199802020030".substring(0, "360521199802020030".length() / 2) + "******");
    }

}

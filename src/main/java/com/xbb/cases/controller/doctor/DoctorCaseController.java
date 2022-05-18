package com.xbb.cases.controller.doctor;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.controller.DataFilter;
import com.xbb.cases.entity.Cases;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.CasesMapper;
import com.xbb.cases.mapper.UserMapper;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor/case")
public class DoctorCaseController extends DataFilter {

    private String prefix = "/doctor/case/";

    @Resource
    private CasesMapper casesMapper;
    @Resource
    private UserMapper userMapper;

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

    // 分页数据
    @ResponseBody
    @RequestMapping("/data")
    public RespResult data(HttpSession session, @RequestParam("page") int page,
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
                (page - 1) * limit, limit, name, cellphone, identityCard, title, status, casesType);
        int count = casesMapper.selectCountByPaging(
                name, cellphone, identityCard, title, status, casesType);
        // 判断是否需要进行匿名操作
        List<Cases> list = casesList.stream().peek(e -> {
            if (!filterEncypt(session, e)) {
                User user = e.getUser();
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
        }).collect(Collectors.toList());
        RespResult respResult = new RespResult();
        respResult.success(list, count);
        return respResult;
    }

    // 添加医嘱
    @RequestMapping("/advice/add.html")
    public String addAdviceHtml(Model model) {
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        return prefix + "advice_add";
    }

    // 添加病例
    @RequestMapping("/case/add.html")
    public String addCaseHtml(Model model) {
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        return prefix + "case_add";
    }

    // 添加检查报告
    @RequestMapping("/inspect/add.html")
    public String addInspectHtml(Model model) {
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        return prefix + "inspect_add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public RespResult add(HttpSession session, @RequestBody Cases cases) {
        cases.setCreatTime(new Date());
        User u = (User) session.getAttribute(SessionConstant.KEY_USER);
        cases.setCreateById(u.getId());
        casesMapper.insertSelective(cases);
        return new RespResult();
    }

    // 编辑医嘱
    @RequestMapping("/advice/edit.html")
    public String editAdviceHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectByPrimaryKey(id);
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        model.addAttribute("cases", cases);
        return prefix + "advice_edit";
    }

    // 编辑病例
    @RequestMapping("/case/edit.html")
    public String editCaseHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectByPrimaryKey(id);
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        model.addAttribute("cases", cases);
        return prefix + "case_edit";
    }

    // 编辑检查报告
    @RequestMapping("/inspect/edit.html")
    public String editInspectHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectByPrimaryKey(id);
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        model.addAttribute("cases", cases);
        return prefix + "inspect_edit";
    }

    @ResponseBody
    @RequestMapping("/edit")
    public RespResult edit(@RequestBody Cases cases) {
        casesMapper.updateByPrimaryKeySelective(cases);
        return new RespResult();
    }

    // 医嘱详情
    @RequestMapping("/advice/item.html")
    public String itemAdviceHtml(HttpSession session, @RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (!filterEncypt(session, cases)) {
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
    public String itemCaseHtml(HttpSession session, @RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (!filterEncypt(session, cases)) {
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
    public String itemInspectHtml(HttpSession session, @RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectById(id);
        if (!filterEncypt(session, cases)) {
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

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody int id) {
        casesMapper.deleteByPrimaryKey(id);
        return new RespResult();
    }
}

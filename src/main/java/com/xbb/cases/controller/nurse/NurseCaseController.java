package com.xbb.cases.controller.nurse;

import com.xbb.cases.common.RespResult;
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
@RequestMapping("/nurse/case")
public class NurseCaseController extends DataFilter {

    private String prefix = "/nurse/case/";

    @Resource
    private CasesMapper casesMapper;
    @Resource
    private UserMapper userMapper;

    // 医嘱管理
    @RequestMapping("/advice.html")
    public String adviceHtml() {
        return prefix + "advice";
    }

    // 护理文书管理
    @RequestMapping("/nursing.html")
    public String nursingHtml() {
        return prefix + "nursing";
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

    // 添加护理文书
    @RequestMapping("/nursing/add.html")
    public String addHtml(Model model) {
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        return prefix + "nursing_add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public RespResult add(@RequestBody Cases cases) {
        cases.setCreatTime(new Date());
        casesMapper.insertSelective(cases);
        return new RespResult();
    }

    // 编辑护理文书
    @RequestMapping("/nursing/edit.html")
    public String editHtml(@RequestParam("id") int id, Model model) {
        Cases cases = casesMapper.selectByPrimaryKey(id);
        List<User> userList = userMapper.selectListByRole(0);
        model.addAttribute("userList", userList);
        model.addAttribute("cases", cases);
        return prefix + "nursing_edit";
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

    // 护理文书详情
    @RequestMapping("/nursing/item.html")
    public String itemHtml(HttpSession session, @RequestParam("id") int id, Model model) {
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
        return prefix + "nursing_item";
    }

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody int id) {
        casesMapper.deleteByPrimaryKey(id);
        return new RespResult();
    }

}

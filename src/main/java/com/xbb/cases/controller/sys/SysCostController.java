package com.xbb.cases.controller.sys;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.Cost;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.CostMapper;
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
@RequestMapping("/sys/cost")
public class SysCostController {

    private String prefix = "/sys/cost/";

    @Resource
    private CostMapper costMapper;

    // 费用管理
    @RequestMapping("/manage.html")
    public String manageHtml() {
        return prefix + "cost";
    }

    // 分页数据
    @ResponseBody
    @RequestMapping("/data")
    public RespResult data(@RequestParam("page") int page,
                           @RequestParam("limit") int limit,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value = "types", required = false, defaultValue = "") String types) {
        if (name.equals("")) name = null;
        if (types.equals("")) types = null;
        List<Cost> costList = costMapper.selectListByPaging(
                (page - 1) * limit,
                limit,
                name,
                types
        );
        int count = costMapper.selectCountByPaging(
                name,
                types
        );
        RespResult respResult = new RespResult();
        respResult.success(costList, count);
        return respResult;
    }

    // 添加
    @ResponseBody
    @RequestMapping("/add")
    public RespResult add(@RequestBody Cost cost) {
        cost.setCreatTime(new Date());
        costMapper.insertSelective(cost);
        return new RespResult();
    }

    // 编辑
    @ResponseBody
    @RequestMapping("/edit")
    public RespResult edit(@RequestBody Cost cost) {
        costMapper.updateByPrimaryKeySelective(cost);
        return new RespResult();
    }

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody int id) {
        costMapper.deleteByPrimaryKey(id);
        return new RespResult();
    }

}

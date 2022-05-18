package com.xbb.cases.controller.doctor;

import com.xbb.cases.common.RespResult;
import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.Leaves;
import com.xbb.cases.entity.User;
import com.xbb.cases.mapper.LeavesMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/doctor/leaves")
public class DoctorLeavesController {

    private String prefix = "/doctor/leaves/";

    @Resource
    private LeavesMapper leavesMapper;

    // 留言管理
    @RequestMapping("/manage.html")
    public String manageHtml() {
        return prefix + "leaves";
    }

    // 分页数据
    @ResponseBody
    @RequestMapping("/data")
    public RespResult data(@RequestParam("page") int page,
                           @RequestParam("limit") int limit,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value = "cellphone", required = false, defaultValue = "") String cellphone,
                           @RequestParam(value = "content", required = false, defaultValue = "") String content,
                           HttpSession session) {
        User user = (User) session.getAttribute(SessionConstant.KEY_USER);
        if (name.equals("")) name = null;
        if (cellphone.equals("")) cellphone = null;
        if (content.equals("")) content = null;
        List<Leaves> leavesList = leavesMapper.selectListByPaging(
                (page - 1) * limit,
                limit,
                name,
                cellphone,
                content,
                user.getId()
        );
        int count = leavesMapper.selectCountByPaging(
                name,
                cellphone,
                content,
                user.getId()
        );
        RespResult respResult = new RespResult();
        respResult.success(leavesList, count);
        return respResult;
    }

    // 留言
    @ResponseBody
    @RequestMapping("/add")
    public RespResult edit(@RequestBody Leaves leaves, HttpSession session) {
        User user = (User) session.getAttribute(SessionConstant.KEY_USER);
        leaves.setUserId(user.getId());
        leaves.setLeavesTime(new Date());
        leavesMapper.insertSelective(leaves);
        return new RespResult();
    }

    // 删除
    @ResponseBody
    @RequestMapping("/delete")
    public RespResult delete(@RequestBody int id) {
        leavesMapper.deleteByPrimaryKey(id);
        return new RespResult();
    }

}

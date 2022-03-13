package org.example.tuole.system.controller;

import org.example.tuole.common.BaseController;
import org.example.tuole.system.dto.ResponseDTO;
import org.example.tuole.system.entity.SysUser;
import org.example.tuole.system.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysUserController extends BaseController {
    private final IndexService indexService;

    public SysUserController(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseDTO getUserInfo() {
        SysUser user = indexService.getUserInfo();
        return ResponseDTO.returnSuccess("操作成功", user);
    }



}

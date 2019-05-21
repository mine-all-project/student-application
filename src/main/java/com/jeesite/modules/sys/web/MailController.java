/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.EmpUser;
import com.jeesite.modules.sys.entity.Mail;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.EmpUserService;
import com.jeesite.modules.sys.service.MailService;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收件箱Controller
 * 
 * @author admin
 * @version 2019-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/mail")
public class MailController extends BaseController {

	@Autowired
	private MailService mailService;
	@Autowired
	private EmpUserService empUserService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Mail get(String id, boolean isNewRecord) {
		return mailService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Mail mail, Model model) {
		User user = UserUtils.getUser();
		EmpUser empUser = empUserService.get(user.getUserCode());
		String officeCode = empUser.getEmployee().getOffice().getOfficeCode();
		if ("student".equals(officeCode)) {
			mail.setStu(user.getUserCode());
			model.addAttribute("mail", mail);
			return "modules/sys/stuMailList";
		} else {
			mail.setCom(user.getUserCode());
			model.addAttribute("mail", mail);
			return "modules/sys/comMailList";
		}
	}

	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Mail> listData(Mail mail, HttpServletRequest request, HttpServletResponse response) {
		mail.setPage(new Page<>(request, response));
		Page<Mail> page = mailService.findPage(mail);
		List<Mail> list = page.getList();
		for (Mail m : list) {
			String com = mail.getCom();
			String stu = mail.getStu();
			if (!StringUtils.isEmpty(com) && com.equals(m.getSend())
					|| !StringUtils.isEmpty(stu) && stu.equals(m.getSend())) {
				m.setSend("发件");
			} else {
				m.setSend("收件");
			}
		}
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Mail mail, Model model) {
		model.addAttribute("mail", mail);
		return "modules/sys/mailForm";
	}

	/**
	 * 保存收件箱
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Mail mail) {
		mailService.save(mail);
		return renderResult(Global.TRUE, text("邮件发送成功！"));
	}

}
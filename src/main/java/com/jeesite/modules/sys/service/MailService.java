/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.dao.EmpUserDao;
import com.jeesite.modules.sys.dao.MailDao;
import com.jeesite.modules.sys.entity.EmpUser;
import com.jeesite.modules.sys.entity.Mail;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收件箱Service
 * 
 * @author admin
 * @version 2019-05-15
 */
@Service
@Transactional(readOnly = true)
public class MailService extends CrudService<MailDao, Mail> {
	@Autowired
	private EmpUserDao empUserDao;

	/**
	 * 获取单条数据
	 * 
	 * @param mail
	 * @return
	 */
	@Override
	public Mail get(Mail mail) {
		return super.get(mail);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param mail 查询条件
	 * @param      mail.page 分页对象
	 * @return
	 */
	@Override
	public Page<Mail> findPage(Mail mail) {
		return super.findPage(mail);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param mail
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(Mail mail) {
		String userCode = UserUtils.getUser().getUserCode();
		EmpUser empUser = new EmpUser();
		empUser.setUserCode(userCode);
		empUser = empUserDao.get(empUser);
		String officeCode = empUser.getEmployee().getOffice().getOfficeCode();
		if ("student".equals(officeCode)) {
			mail.setStu(userCode);
		} else {
			mail.setCom(userCode);
		}
		mail.setSend(userCode);
		super.save(mail);
	}

	/**
	 * 更新状态
	 * 
	 * @param mail
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStatus(Mail mail) {
		super.updateStatus(mail);
	}

	/**
	 * 删除数据
	 * 
	 * @param mail
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(Mail mail) {
		super.delete(mail);
	}

}
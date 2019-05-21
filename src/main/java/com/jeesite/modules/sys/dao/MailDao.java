/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Mail;

/**
 * 收件箱DAO接口
 * @author admin
 * @version 2019-05-15
 */
@MyBatisDao
public interface MailDao extends CrudDao<Mail> {
	
}
/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 收件箱Entity
 * 
 * @author admin
 * @version 2019-05-15
 */
@Table(name = "${_prefix}sys_mail", alias = "a", columns = {
		@Column(name = "id", attrName = "id", label = "主键ID", isPK = true),
		@Column(name = "stu", attrName = "stu", label = "学生账号"),
		@Column(name = "com", attrName = "com", label = "公司账号"),
		@Column(name = "remarks", attrName = "remarks", label = "备注"),
		@Column(name = "send", attrName = "send", label = "发送方"),
		@Column(name = "inputdate", attrName = "inputdate", label = "日期"), }, orderBy = "a.inputdate DESC")
public class Mail extends DataEntity<Mail> {

	private static final long serialVersionUID = 1L;
	private String stu; // 学生账号
	private String stuname; // 学生姓名
	private String com; // 公司账号
	private String comname; // 公司名称
	private String remarks; // 备注
	private String send; // 发送方
	private Date inputdate; // 日期

	public Mail() {
		this(null);
	}

	public Mail(String id) {
		super(id);
	}

	@Length(min = 0, max = 60, message = "学生账号长度不能超过 60 个字符")
	public String getStu() {
		return stu;
	}

	public void setStu(String stu) {
		this.stu = stu;
	}

	@Length(min = 0, max = 60, message = "公司账号长度不能超过 60 个字符")
	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInputdate() {
		return inputdate;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	public Date getInputdate_gte() {
		return sqlMap.getWhere().getValue("inputdate", QueryType.GTE);
	}

	public void setInputdate_gte(Date inputdate) {
		sqlMap.getWhere().and("inputdate", QueryType.GTE, inputdate);
	}

	public Date getInputdate_lte() {
		return sqlMap.getWhere().getValue("inputdate", QueryType.LTE);
	}

	public void setInputdate_lte(Date inputdate) {
		sqlMap.getWhere().and("inputdate", QueryType.LTE, inputdate);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

}
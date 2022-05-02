package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 公司信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-02-21 11:54:59
 */
@TableName("gongsixinxi")
public class GongsixinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GongsixinxiEntity() {
		
	}
	
	public GongsixinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 公司名称
	 */
					
	private String gongsimingcheng;
	
	/**
	 * 公司环境
	 */
					
	private String gongsihuanjing;
	
	/**
	 * 公司地址
	 */
					
	private String gongsidizhi;
	
	/**
	 * 法人
	 */
					
	private String faren;
	
	/**
	 * 联系方式
	 */
					
	private String lianxifangshi;
	
	/**
	 * 注册资金
	 */
					
	private String zhucezijin;
	
	/**
	 * 公司文化
	 */
					
	private String gongsiwenhua;
	
	/**
	 * 公司产品
	 */
					
	private String gongsichanpin;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：公司名称
	 */
	public void setGongsimingcheng(String gongsimingcheng) {
		this.gongsimingcheng = gongsimingcheng;
	}
	/**
	 * 获取：公司名称
	 */
	public String getGongsimingcheng() {
		return gongsimingcheng;
	}
	/**
	 * 设置：公司环境
	 */
	public void setGongsihuanjing(String gongsihuanjing) {
		this.gongsihuanjing = gongsihuanjing;
	}
	/**
	 * 获取：公司环境
	 */
	public String getGongsihuanjing() {
		return gongsihuanjing;
	}
	/**
	 * 设置：公司地址
	 */
	public void setGongsidizhi(String gongsidizhi) {
		this.gongsidizhi = gongsidizhi;
	}
	/**
	 * 获取：公司地址
	 */
	public String getGongsidizhi() {
		return gongsidizhi;
	}
	/**
	 * 设置：法人
	 */
	public void setFaren(String faren) {
		this.faren = faren;
	}
	/**
	 * 获取：法人
	 */
	public String getFaren() {
		return faren;
	}
	/**
	 * 设置：联系方式
	 */
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	/**
	 * 获取：联系方式
	 */
	public String getLianxifangshi() {
		return lianxifangshi;
	}
	/**
	 * 设置：注册资金
	 */
	public void setZhucezijin(String zhucezijin) {
		this.zhucezijin = zhucezijin;
	}
	/**
	 * 获取：注册资金
	 */
	public String getZhucezijin() {
		return zhucezijin;
	}
	/**
	 * 设置：公司文化
	 */
	public void setGongsiwenhua(String gongsiwenhua) {
		this.gongsiwenhua = gongsiwenhua;
	}
	/**
	 * 获取：公司文化
	 */
	public String getGongsiwenhua() {
		return gongsiwenhua;
	}
	/**
	 * 设置：公司产品
	 */
	public void setGongsichanpin(String gongsichanpin) {
		this.gongsichanpin = gongsichanpin;
	}
	/**
	 * 获取：公司产品
	 */
	public String getGongsichanpin() {
		return gongsichanpin;
	}

}

package com.entity.vo;

import com.entity.GongsixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 公司信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2022-02-21 11:54:59
 */
public class GongsixinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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

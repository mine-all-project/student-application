package com.entity.model;

import com.entity.HuodongxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 活动信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2022-02-21 11:54:59
 */
public class HuodongxinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 活动图片
	 */
	
	private String huodongtupian;
		
	/**
	 * 活动地址
	 */
	
	private String huodongdizhi;
		
	/**
	 * 活动日期
	 */
	
	private String huodongriqi;
		
	/**
	 * 活动简介
	 */
	
	private String huodongjianjie;
		
	/**
	 * 活动内容
	 */
	
	private String huodongneirong;
		
	/**
	 * 举办公司
	 */
	
	private String jubangongsi;
		
	/**
	 * 发布日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date faburiqi;
				
	
	/**
	 * 设置：活动图片
	 */
	 
	public void setHuodongtupian(String huodongtupian) {
		this.huodongtupian = huodongtupian;
	}
	
	/**
	 * 获取：活动图片
	 */
	public String getHuodongtupian() {
		return huodongtupian;
	}
				
	
	/**
	 * 设置：活动地址
	 */
	 
	public void setHuodongdizhi(String huodongdizhi) {
		this.huodongdizhi = huodongdizhi;
	}
	
	/**
	 * 获取：活动地址
	 */
	public String getHuodongdizhi() {
		return huodongdizhi;
	}
				
	
	/**
	 * 设置：活动日期
	 */
	 
	public void setHuodongriqi(String huodongriqi) {
		this.huodongriqi = huodongriqi;
	}
	
	/**
	 * 获取：活动日期
	 */
	public String getHuodongriqi() {
		return huodongriqi;
	}
				
	
	/**
	 * 设置：活动简介
	 */
	 
	public void setHuodongjianjie(String huodongjianjie) {
		this.huodongjianjie = huodongjianjie;
	}
	
	/**
	 * 获取：活动简介
	 */
	public String getHuodongjianjie() {
		return huodongjianjie;
	}
				
	
	/**
	 * 设置：活动内容
	 */
	 
	public void setHuodongneirong(String huodongneirong) {
		this.huodongneirong = huodongneirong;
	}
	
	/**
	 * 获取：活动内容
	 */
	public String getHuodongneirong() {
		return huodongneirong;
	}
				
	
	/**
	 * 设置：举办公司
	 */
	 
	public void setJubangongsi(String jubangongsi) {
		this.jubangongsi = jubangongsi;
	}
	
	/**
	 * 获取：举办公司
	 */
	public String getJubangongsi() {
		return jubangongsi;
	}
				
	
	/**
	 * 设置：发布日期
	 */
	 
	public void setFaburiqi(Date faburiqi) {
		this.faburiqi = faburiqi;
	}
	
	/**
	 * 获取：发布日期
	 */
	public Date getFaburiqi() {
		return faburiqi;
	}
			
}

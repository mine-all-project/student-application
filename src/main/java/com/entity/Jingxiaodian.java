package com.entity;

import com.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;


/**
 * 经销店表
 * 2022/4/28 21:35:39
 */
@Entity
public class Jingxiaodian extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column
    private String name;
    @Column
    private String xiangpian;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXiangpian() {
        return xiangpian;
    }

    public void setXiangpian(String xiangpian) {
        this.xiangpian = xiangpian;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}

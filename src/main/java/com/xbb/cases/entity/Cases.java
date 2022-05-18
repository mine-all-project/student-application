package com.xbb.cases.entity;

import java.util.Date;

public class Cases {
    private User createBy;
    private Integer createById;
    private Integer id;
    private String title;
    private Integer userId;
    private Integer status;
    private Date creatTime;
    private Integer casesType;
    private String startHospitalized;
    private String endHospitalized;
    private String startReason;
    private String endReason;
    private String record;
    private String bloodType;
    private String surgicalRecord;
    private String autograph;
    private String content;
    private User user;

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public Integer getCasesType() {
        return casesType;
    }
    public void setCasesType(Integer casesType) {
        this.casesType = casesType;
    }
    public String getStartHospitalized() {
        return startHospitalized;
    }
    public void setStartHospitalized(String startHospitalized) {
        this.startHospitalized = startHospitalized;
    }
    public String getEndHospitalized() {
        return endHospitalized;
    }
    public void setEndHospitalized(String endHospitalized) {
        this.endHospitalized = endHospitalized;
    }
    public String getStartReason() {
        return startReason;
    }
    public void setStartReason(String startReason) {
        this.startReason = startReason;
    }
    public String getEndReason() {
        return endReason;
    }
    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }
    public String getRecord() {
        return record;
    }
    public void setRecord(String record) {
        this.record = record;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getSurgicalRecord() {
        return surgicalRecord;
    }
    public void setSurgicalRecord(String surgicalRecord) {
        this.surgicalRecord = surgicalRecord;
    }
    public String getAutograph() {
        return autograph;
    }
    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

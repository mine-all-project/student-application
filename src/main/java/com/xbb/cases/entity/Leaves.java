package com.xbb.cases.entity;

import java.util.Date;

public class Leaves {
    private Integer id;

    private Integer userId;

    private String content;

    private Date leavesTime;

    private String replyCon;

    private Date replyTime;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLeavesTime() {
        return leavesTime;
    }

    public void setLeavesTime(Date leavesTime) {
        this.leavesTime = leavesTime;
    }

    public String getReplyCon() {
        return replyCon;
    }

    public void setReplyCon(String replyCon) {
        this.replyCon = replyCon;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
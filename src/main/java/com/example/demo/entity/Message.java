package com.example.demo.entity;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Message extends BaseEntity{
    @Column(length = 50)
    private String fromId;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String toId;
    @Column(length = 1)
    private String status;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

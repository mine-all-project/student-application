package com.entity;

import com.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 预约表
 */
@Entity
public class Yuyue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column
    private String type;
    @Column
    private String address;
    @Column
    private LocalDate date;
    @Column
    private String status;
    @ManyToOne
    private Jingxiaodian store;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Jingxiaodian getStore() {
        return store;
    }

    public void setStore(Jingxiaodian store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

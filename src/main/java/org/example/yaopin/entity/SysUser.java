package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SysUser extends BaseEntity {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Column(columnDefinition = "varchar(32) comment '用户名'", unique = true)
    private String username;

    @Column(columnDefinition = "varchar(64) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(32) comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(32) comment '邮箱'")
    private String mail;

    @Column(columnDefinition = "varchar(11) comment '手机号'")
    private String phone;

    @Column(columnDefinition = "bit(1) default 0 not null comment '用户状态标记 0:正常 1:禁用'")
    private Integer status;

    @Column(columnDefinition = "varchar(1) default 0 not null comment '用户身份 0:管理员 1：采购员 2：库存员 3：销售员'")
    private String role;

    private boolean isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

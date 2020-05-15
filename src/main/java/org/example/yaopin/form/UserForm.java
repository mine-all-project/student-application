package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;
import org.example.yaopin.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class UserForm {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotBlank(message = "id不能为空", groups = IsEdit.class)
    @NotBlank(message = "id不能为空", groups = IsStatusChange.class)
//    @Null(message = "id必须为空", groups = IsAdd.class)
    private String id;
    private LocalDateTime createTime;

    @Length(max = 32, message = "长度错误", groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {IsAdd.class, IsLogin.class})
    private String username;

    @Length(max = 32, message = "长度错误", groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "旧密码不能为空", groups = {IsLogin.class, IsEdit.class})
    private String password;

    @Length(max = 32, message = "长度错误", groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "新密码不能为空", groups = {IsNotNull.class, IsEdit.class})
    private String newPassword;

    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;

    @Length(max = 32)
    @NotBlank(message = "电话不能为空", groups = {IsAdd.class, IsEdit.class})
    private String phone;

    @Length(max = 32)
    @NotBlank(message = "邮箱不能为空", groups = {IsAdd.class, IsEdit.class})
    private String mail;

    private String role;

    public SysUser toEntity() {
        SysUser entity = new SysUser();
        entity.setId(this.id);
        entity.setCreateTime(this.createTime);
        entity.setName(this.name);
        entity.setPhone(this.phone);
        entity.setMail(this.mail);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        return entity;
    }
}

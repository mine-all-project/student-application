package org.example.fangwuzulin.form;

import org.example.fangwuzulin.entity.SysUser;
import lombok.Data;

@Data
public class UserForm {
    private String id;
    private String username;
    private String password;
    private String newPassword;
    private String name;
    private String phone;
    private String mail;
    private String randomCode;

    public SysUser toEntity() {
        SysUser entity = new SysUser();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setPhone(this.phone);
        entity.setMail(this.mail);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setRandomCode(this.randomCode);
        return entity;
    }
}

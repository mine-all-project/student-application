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

    public SysUser toEntity() {
        SysUser user = new SysUser();
        user.setId(this.id);
        user.setName(this.name);
        user.setPhone(this.phone);
        user.setMail(this.mail);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}

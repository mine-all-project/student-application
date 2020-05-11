package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;
import org.example.yaopin.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {
    @NotBlank(message = "id不能为空", groups = IsEdit.class)
    @NotBlank(message = "id不能为空", groups = IsStatusChange.class)
    @Null(message = "id必须为空", groups = IsAdd.class)
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

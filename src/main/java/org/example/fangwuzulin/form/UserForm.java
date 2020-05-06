package org.example.fangwuzulin.form;

import org.example.fangwuzulin.config.groups.*;
import org.example.fangwuzulin.entity.SysUser;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserForm {
    @NotBlank(message = "id不能为空", groups = IsEdit.class)
    @NotBlank(message = "id不能为空", groups = IsStatusChange.class)
    @Null(message = "id必须为空", groups = IsAdd.class)
    private String id;

    @Length(max = 32 ,message = "长度错误",groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String username;

    @Length(max = 32 ,message = "长度错误",groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String password;

    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {IsAdd.class,IsEdit.class})
    private String name;

    public SysUser toEntity(){
        SysUser user = new SysUser();
        user.setId(this.id);
        user.setName(this.name);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}

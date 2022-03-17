package org.example.application.system.form;

import org.example.application.common.BaseForm;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.groups.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * TODO 用户信息提交Form
 *

 * 2020/1/27 11:26



 */
@Getter
@Setter
public class UserForm extends BaseForm {
    @NotBlank(message = "id不能为空", groups = IsEdit.class)
    @NotBlank(message = "id不能为空", groups = IsStatusChange.class)
    @Null(message = "id必须为空", groups = IsAdd.class)
    private String id;

    @Length(max = 32, message = "长度错误", groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String username;

    @Length(max = 32, message = "长度错误", groups = {IsCheckLength.class, IsLogin.class})
    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String password;

    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;

    @Length(max = 32)
    @NotBlank(message = "邮箱不能为空", groups = {IsAdd.class, IsEdit.class})
    private String mail;

    @Length(max = 3)
    @NotNull(message = "年龄不能为空", groups = {IsAdd.class, IsEdit.class})
    private Integer age;
    private List<String> tags;

    private Integer role;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

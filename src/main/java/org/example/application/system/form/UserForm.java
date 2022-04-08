package org.example.application.system.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.ApplicationException;
import org.example.application.common.BaseForm;
import org.example.application.common.Groups;
import org.example.application.system.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * TODO 用户信息提交Form
 */
@Getter
@Setter
public class UserForm extends BaseForm<SysUser> {
    @NotBlank(message = "id不能为空", groups = Groups.IsEdit.class)
    @NotBlank(message = "id不能为空", groups = Groups.IsStatusChange.class)
    @Null(message = "id必须为空", groups = Groups.IsAdd.class)
    private String id;

    @Length(max = 32, message = "长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {Groups.IsAdd.class, Groups.IsNotNull.class, Groups.IsLogin.class})
    private String username;

    @Length(max = 32, message = "长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class})
    @NotBlank(message = "密码不能为空", groups = {Groups.IsAdd.class, Groups.IsNotNull.class, Groups.IsLogin.class})
    private String password;

    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private String name;

    @Length(max = 3)
    @NotNull(message = "年龄不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private Integer age;

    private Integer role;

    @Override
    public SysUser toEntity() {
        throw new ApplicationException("暂无转换方法");
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

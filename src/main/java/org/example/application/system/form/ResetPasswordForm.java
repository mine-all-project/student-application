package org.example.application.system.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.ApplicationException;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsLogin;
import org.example.application.common.groups.IsNotNull;
import org.example.application.system.entity.SysUser;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ResetPasswordForm extends BaseForm<SysUser> {
    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;

    private String oldPassword;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String newPassword;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String againPassword;

    @Override
    public SysUser toEntity() {
        throw new ApplicationException("暂无转换方法");
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

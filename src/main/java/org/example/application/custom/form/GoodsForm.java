package org.example.application.custom.form;

import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsLogin;
import org.example.application.common.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * TODO 用户信息提交Form
 *

 * 2020/1/27 11:26
 * e-mail


 */
@Getter
@Setter
public class GoodsForm extends BaseForm {
    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;

    private String oldPassword;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String password;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String rePassword;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

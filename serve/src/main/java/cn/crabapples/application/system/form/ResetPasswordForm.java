package cn.crabapples.application.system.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsLogin;
import cn.crabapples.application.common.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * TODO 用户信息提交Form
 *
 * @author Mr.He
 * 2020/1/27 11:26
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public class ResetPasswordForm extends BaseForm {
    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String password;

    @NotBlank(message = "密码不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String rePassword;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

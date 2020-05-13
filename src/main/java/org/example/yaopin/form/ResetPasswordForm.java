package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ResetPasswordForm {
    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;

    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;
}

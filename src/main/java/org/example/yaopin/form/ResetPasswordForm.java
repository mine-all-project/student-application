package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;

import javax.validation.constraints.NotBlank;

public class ResetPasswordForm {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;

    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;
}

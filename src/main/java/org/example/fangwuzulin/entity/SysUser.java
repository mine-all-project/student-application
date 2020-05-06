package org.example.fangwuzulin.entity;

import org.example.fangwuzulin.config.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SysUser extends BaseEntity {
    @NotBlank(message = "用户名不能为空", groups = IsNotNull.class)
    private String username;

    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;

    @NotBlank(message = "姓名不能为空", groups = IsNotNull.class)
    private String name;

    @NotBlank(message = "邮箱不能为空", groups = IsNotNull.class)
    private String mail;

    @NotBlank(message = "手机号不能为空", groups = IsNotNull.class)
    private String phone;

    @NotBlank(message = "状态不能为空", groups = IsNotNull.class)
    private Integer status;

    private boolean isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

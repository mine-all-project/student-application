package org.example.yaopin.entity;

import org.example.yaopin.config.groups.IsNotNull;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class SysUser extends BaseEntity {
    @Column(columnDefinition = "varchar(32) comment '用户名'",unique=true)
    @NotBlank(message = "用户名不能为空", groups = IsNotNull.class)
    private String username;

    @Column(columnDefinition = "varchar(64) comment '密码'")
    @NotBlank(message = "密码不能为空", groups = IsNotNull.class)
    private String password;

    @Column(columnDefinition = "varchar(32) comment '姓名'")
    @NotBlank(message = "姓名不能为空", groups = IsNotNull.class)
    private String name;

    @Column(columnDefinition = "varchar(32) comment '邮箱'")
    @NotBlank(message = "邮箱不能为空", groups = IsNotNull.class)
    private String mail;

    @Column(columnDefinition = "varchar(11) comment '手机号'")
    @NotBlank(message = "手机号不能为空", groups = IsNotNull.class)
    private String phone;

    @Column(columnDefinition = "bit(1) default 0 not null comment '用户状态标记 0:正常 1:禁用'")
    @NotBlank(message = "状态不能为空", groups = IsNotNull.class)
    private Integer status;

    private boolean isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

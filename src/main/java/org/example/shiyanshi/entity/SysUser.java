package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SysUser extends BaseEntity {
    @Column(columnDefinition = "varchar(32) comment '用户名'", unique = true)
    private String username;

    @Column(columnDefinition = "varchar(64) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(32) comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(32) comment '邮箱'")
    private String mail;

    @Column(columnDefinition = "varchar(11) comment '手机号'")
    private String phone;

    @Column(columnDefinition = "bit(1) default 0 not null comment '用户状态标记 0:正常 1:禁用'")
    private Integer status;

    @Column(columnDefinition = "varchar(1) default 0 not null comment '用户身份 0:管理员 1：采购员 2：库存员 3：销售员'")
    private String role;

    private boolean isAdmin;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

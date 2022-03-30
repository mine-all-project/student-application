package org.example.application.system.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SysUser extends BaseEntity {
    @Column(columnDefinition = "varchar(32) comment '用户名' ", unique = true)
    private String username;
    @Column(columnDefinition = "varchar(64)  comment '密码'")
    private String password;
    @Column(columnDefinition = "varchar(32)  comment '姓名'")
    private String name;
    @OneToOne
    private FileInfo headImg;
    @Column(columnDefinition = "tinyint  comment '年龄'")
    private Integer age;
    @Column(columnDefinition = "tinyint  comment '权限 0:普通管理员 1:普通用户'")
    private Integer role;
    @Column(columnDefinition = "tinyint  comment '状态 0:正常 1:禁用'")
    private int status;

    @Column(columnDefinition = "tinyint default 1 comment '权限 0:允许管理账单 1:禁止管理账单'")
    private Integer orderStatus;
    @Column(columnDefinition = "tinyint default 1 comment '权限 0:允许管理账务 1:禁止管理账务'")
    private Integer orderCountStatus;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

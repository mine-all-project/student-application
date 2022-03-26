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
    @Column(columnDefinition = "int ")
    private Integer age;
    //角色 0:系统管理员 1:普通管理员 2: 普通用户
    private Integer role;
    //用户状态标记 0:正常 1:禁用
    private int status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

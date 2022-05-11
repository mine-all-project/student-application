package org.example.application.system.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SystemUser extends BaseEntity {
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
    @Column(columnDefinition = "tinyint  comment '权限 0:管理员 1:系主任 2:教师 3:学生'")
    private Integer role;
    @Column(columnDefinition = "tinyint  comment '状态 0:正常 1:禁用'")
    private int status;

    //    @Column(columnDefinition = "tinyint default 1 comment '权限 0:允许管理商品 1:禁止管理商品'")
//    private Integer goodsStatus;
//    @Column(columnDefinition = "tinyint default 1 comment '权限 0:允许管理信息 1:禁止管理信息'")
//    private Integer paperStatus;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

package org.example.application.system.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(schema = "system")
public class SysUser extends BaseEntity {
    //用户名
    @Column(columnDefinition = "varchar(32) ", unique = true)
    private String username;
    //密码
    @Column(columnDefinition = "varchar(64) ")
    private String password;
    //姓名
    @Column(columnDefinition = "varchar(32) ")
    private String name;
    //头像
    @OneToOne
    @JoinColumn(columnDefinition = "")
    private FileInfo headImg;
    //年龄
    @Column(columnDefinition = "int ")
    private Integer age;
    //角色 0:系统管理员 1:普通管理员 2: 普通用户
    private Integer role;
    //用户状态标记 0:正常 1:禁用
    private int status;
    //是否允许发布状态标记 0:正常 1:禁用
    private int publishStatus;
    //是否运行评论状态标记 0:正常 1:禁用
    private int talkStatus;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

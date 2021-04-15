package cn.crabapples.application.system.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.custom.entity.Tags;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * TODO 用户实体类
 *

 * 2019/7/4 14:51



 * <p>
 * Entity 表示这是一个和数据库表相关联的类
 */
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

    @Column(columnDefinition = "varchar(64) comment '邮箱'")
    private String mail;

    @Column(columnDefinition = "tinyint(4) comment '年龄'")
    private int age;


    @Column(columnDefinition = "tinyint(1) comment '角色 0:超级管理员 1:科研人员 2: 科研管理员'")
    private int role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    private List<SysRole> sysRoles;

    @ManyToMany
    private List<Tags> tags;

    @Column(columnDefinition = "bit(1) default 0 not null comment '用户状态标记 0:正常 1:禁用'")
    private int status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

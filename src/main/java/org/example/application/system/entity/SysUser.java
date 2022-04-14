package org.example.application.system.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(schema = "system")
public class SysUser extends BaseEntity {
    //用户名
    @Column(unique = true)
    private String username;
    //密码
    @Column
    private String password;
    //姓名
    @Column
    private String name;
    //头像
    @ManyToOne
    private FileInfo headImg;
    //角色 0:系统管理员 1:普通管理员 2: 普通用户
    private Integer role;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

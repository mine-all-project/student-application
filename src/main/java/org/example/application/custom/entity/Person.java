package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Person extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '姓名' ")
    private String name;
    @Column(columnDefinition = "tinyint default 0  comment '性别'")
    private Integer gender;
    @Column(columnDefinition = "varchar(64) default null comment '手机号'")
    private String phone;
    @Column(columnDefinition = "decimal(10,2) default null comment '钱包'")
    private BigDecimal money;
    @Column(columnDefinition = "varchar(64) default null comment '运营商'")
    private String type;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

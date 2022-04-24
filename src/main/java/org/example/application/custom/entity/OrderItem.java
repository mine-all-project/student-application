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
public class OrderItem extends BaseEntity {
    @Column(columnDefinition = "tinyint default 1 comment '1:月固定账单 2:增值业务费'")
    private Integer type;
    @Column(columnDefinition = "decimal(10,2) default null comment '金额'")
    private BigDecimal money;
    @Column(columnDefinition = "varchar(64) default null comment '名称'")
    private String name;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

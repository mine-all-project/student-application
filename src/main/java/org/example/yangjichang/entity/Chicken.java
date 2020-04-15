package org.example.yangjichang.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Chicken extends BaseEntity {
    private String name;
    private BigDecimal sale;
    private Integer remaining;
    @Column(columnDefinition = "varchar(10) comment '1公鸡 2母鸡 3小鸡'")
    private String type;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

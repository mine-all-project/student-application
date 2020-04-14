package org.example.yangjichang.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/4/2 20:48
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
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

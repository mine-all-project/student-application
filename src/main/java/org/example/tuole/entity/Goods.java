package org.example.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
/**
 * 商品表
 */
public class Goods extends BaseEntity {
    private String name;
    private BigDecimal sale;
    @Column(columnDefinition = "longtext")
    private String url;
    @Column(columnDefinition = "longtext")
    private String link;
    @Column(columnDefinition = "varchar(64)")
    private String keyWord;
    private Integer sort;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

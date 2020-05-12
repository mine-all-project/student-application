package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Getter
@Setter
/**
 * 库存信息
 */
public class Storage extends BaseEntity {
    @OneToOne
    private Purchases purchases;
    @OneToOne
    private Goods goods;
    @Column(columnDefinition = "bit(1) default 0 not null comment '状态标记 0:入库 1:出库'")
    private Integer type;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

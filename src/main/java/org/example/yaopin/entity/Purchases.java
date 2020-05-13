package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
/**
 * 采购信息
 */
public class Purchases extends BaseEntity {
    @OneToOne
    private Goods goods;
    @Column(columnDefinition = "varchar(32) comment '产品名称'")
    private String name;
    @Column(columnDefinition = "varchar(32) comment '供应商'")
    private String supply;
    @Column(columnDefinition = "varchar(32) comment '联系电话'")
    private String phone;
    @Column(columnDefinition = "int(10) comment '数量'")
    private Long counts;
    @Column(columnDefinition = "tinyint(1) default 0 not null comment '状态标记 0:未入库 1:已入库'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

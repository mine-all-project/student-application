package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@Getter
@Setter
/**
 * 药品信息
 */
@Entity
public class Goods extends BaseEntity {

    @OneToOne
    private Purchases purchases;

    //    @Column(columnDefinition = "comment '生产日期'")
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate producedTime;

    //    @Column(columnDefinition = "comment '有效期'")
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate shelLife;

    @Column(columnDefinition = "longtext comment '性状'")
    private String characte;

    @Column(columnDefinition = "longtext comment '功能主治'")
    private String majorFunction;

    @Column(columnDefinition = "longtext comment '规格'")
    private String specification;

    @Column(columnDefinition = "longtext comment '用法用量'")
    private String usages;

    @Column(columnDefinition = "longtext comment '不良反应'")
    private String adverseReactions;

    @Column(columnDefinition = "longtext comment '注意事项'")
    private String mattersNeeding;

    @Column(columnDefinition = "longtext comment '禁忌'")
    private String taboo;

    @Column(columnDefinition = "longtext comment '存储环境'")
    private String storageEnvironment;

    @Column(columnDefinition = "tinyint(1) default 0 not null comment '状态标记 0:已入库 1:未入库'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

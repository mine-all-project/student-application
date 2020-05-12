package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Goods extends BaseEntity {


//    producedTime: '生产日期',
//    shelLife: '有效期',
//    character: '性状',
//    majorFunction: '主要治疗咳嗽',
//    specification: '规格',
//    usage: '每日一次，每次半斤',
//    adverseReactions: '不良反应',
//    mattersNeeding: '注意事项',
//    taboo: '忌辛辣，烟酒',
//    storageEnvironment: '避光，密闭储存',

    @OneToOne
    private Purchases purchases;

    @Column(columnDefinition = "comment '生产日期'")
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate producedTime;

    @Column(columnDefinition = "comment '有效期'")
    @JSONField(format = "yyyy-MM-dd")
    private String shelLife;

    @Column(columnDefinition = "longtext comment '性状'")
    private String character;

    @Column(columnDefinition = "longtext comment '功能主治'")
    private String majorFunction;

    @Column(columnDefinition = "longtext comment '规格'")
    private String phone;

    @Column(columnDefinition = "longtext comment '功能主治'")
    private String specification;

    @Column(columnDefinition = "longtext comment '用法用量'")
    private String usage;

    @Column(columnDefinition = "longtext comment '不良反应'")
    private String adverseReactions;

    @Column(columnDefinition = "longtext comment '注意事项'")
    private String mattersNeeding;

    @Column(columnDefinition = "longtext comment '禁忌'")
    private String taboo;

    @Column(columnDefinition = "longtext comment '存储环境'")
    private String storageEnvironment;

    @Column(columnDefinition = "bit(1) default 0 not null comment '状态标记 0:已入库 1:未入库'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

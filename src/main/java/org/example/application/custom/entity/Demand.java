package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class Demand extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '原文件名'")
    private String oldName;
    @Column(columnDefinition = "varchar(256) default null comment '文件虚拟访问路径'")
    private String virtualPath;
    @Column(columnDefinition = "varchar(256) default null comment '文件真实保存访问路径'")
    private String uploadPath;
    @Column(columnDefinition = "varchar(256) default null comment '文件类型'")
    private String demand;
    @Column(columnDefinition = "tinyint default null comment '商品类型 1:闲置 2:租赁'")
    private Integer type;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

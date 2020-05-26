package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
/*
 * 设备信息
 */
public class Machines extends BaseEntity {
    public Machines() {
        this.lineCount = 0;
        this.useCount = 0;
        this.status = 0;
        this.timeCount = 0;
    }

    @ManyToMany
    private List<LineUps> lineUps;
    @Column(columnDefinition = "varchar(20) not null comment '设备名'")
    private String name;
    @Column(columnDefinition = "int(20) not null comment '单次运行时长'")
    private Integer time;
    @Column(columnDefinition = "int(20) default 0 comment '总运行时长'")
    private Integer timeCount;
    @Column(columnDefinition = "longtext comment '注意事项'")
    private String content;
    @Column(columnDefinition = "int(20) not null comment '预约次数'")
    private Integer lineCount;
    @Column(columnDefinition = "int(10) not null comment '使用次数'")
    private Integer useCount;
    @Column(columnDefinition = "tinyint(1) not null default 0 comment '状态标记 0:正常 1:已被预约'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

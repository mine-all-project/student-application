package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
/*
 * 设备信息
 */
public class Machines extends BaseEntity {
    @ManyToMany
    private List<LineUps> lineUps;
    @ManyToOne
    private Rooms rooms;
    @Column(columnDefinition = "varchar(20) not null comment '设备名'")
    private String name;
    @Column(columnDefinition = "int(20) not null comment '预约次数'")
    private Integer lineCount;
    @Column(columnDefinition = "int(20) not null comment '运行时长'")
    private Integer time;
    @Column(columnDefinition = "int(10) not null comment '使用次数'")
    private Integer useCount;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

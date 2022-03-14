package org.example.textreader.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.textreader.system.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Setter
@Getter
@Entity
public class ReadInfo extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '发音'")
    private String voice;
    @Column(columnDefinition = "int default null comment '语调'")
    private Integer pitchRate;
    @Column(columnDefinition = "int default null comment '语速'")
    private Integer speechRate;
    @Column(columnDefinition = "varchar(256) default null comment '文件真实路径'")
    private String filePath;
    @Column(columnDefinition = "varchar(256) default null comment '文件虚拟访问路径'")
    private String virtualPath;
    @Column(columnDefinition = "tinyint default 1 comment '状态 0:正常 1:合成中 2:合成失败'")
    private Integer status;
    @OneToOne
    private Paper paper;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class CarPooling extends BaseEntity {
    //名称
    @Column(columnDefinition = "varchar(256) default null ")
    private String title;
    //人数
    @Column(columnDefinition = "int default null ")
    private Integer personCount;
    //出发地
    @Column(columnDefinition = "varchar(256) default null ")
    private String fromAddress;
    //目的地
    @Column(columnDefinition = "varchar(256) default null ")
    private String targetAddress;
    //详情
    @Column(columnDefinition = "varchar(256) default null ")
    private String content;
    //出发时间
    @Column(columnDefinition = "timestamp  ")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    //状态 0:正常 1:待审 2:驳回 
    private Integer status;
    //审核备注
    @Column(columnDefinition = "varchar(256) default null ")
    private String note;
    @OneToOne
    private SysUser publisher;
    @OneToMany
    private List<FileInfo> images;
    @OneToMany
    private List<Message> messages;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

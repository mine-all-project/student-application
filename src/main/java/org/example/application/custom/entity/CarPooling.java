package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class CarPooling extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '名称'")
    private String title;
    @Column(columnDefinition = "tinyint default null comment '人数'")
    private Integer personCount;
    @Column(columnDefinition = "varchar(256) default null comment '出发地'")
    private String fromAddress;
    @Column(columnDefinition = "varchar(256) default null comment '目的地'")
    private String targetAddress;
    @Column(columnDefinition = "timestamp  comment '出发时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime beginTime;
    @Column(columnDefinition = "tinyint default 1 comment '状态 0:正常 1:待审 2:驳回 3:'")
    private Integer status;
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

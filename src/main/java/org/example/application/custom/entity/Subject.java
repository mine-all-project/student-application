package org.example.application.custom.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SystemUser;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Subject extends BaseEntity {
    @Column(columnDefinition = "varchar(64) not null comment '课题名称'")
    private String name;
    @Column(columnDefinition = "longtext comment '简介'")
    private String description;
    @Column(columnDefinition = "tinyint default 0 comment '是否可选'")
    private Integer isSelect;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default 0 comment '答辩日期'")
    private LocalDateTime time;
    @Column(columnDefinition = "varchar(64) default 0 comment '答辩地点'")
    private String address;
    @Column(columnDefinition = "varchar(64) default null comment '答辩现场用表url'")
    private String paperPdfUrl;
    @Column(columnDefinition = "decimal(10,2) default 0 comment '分数'")
    private BigDecimal score;
    @Column(columnDefinition = "longtext comment '最终评语'")
    private String note;
    @Column(columnDefinition = "varchar(64) default null comment '最终成绩表url'")
    private String scorePdfUrl;
    @Column(columnDefinition = "tinyint default null comment '是否为优秀'")
    private Integer isGood;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default 0 comment '二次答辩日期'")
    private LocalDateTime twoTime;
    @ManyToMany
    private List<SystemUser> groups;
    @OneToOne
    private GuideTeacher guideTeacher;
    @OneToOne
    private ReviewTeacher reviewTeacher;
    @ManyToOne
    private SystemUser reviewer;
    @OneToOne
    private SystemUser selecter;
    @ManyToOne
    @CreatedBy
    private SystemUser createBy;
    @ManyToMany
    private List<Status> status;
}

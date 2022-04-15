package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Custom extends BaseEntity {
    @Column(columnDefinition = "longtext default null comment '全局通知'")
    private String baseNotice;
    @Column(columnDefinition = "longtext default null comment '体温通知'")
    private String temperatureNotice;
    @ManyToMany
    private List<FileInfo> banner;
    @Column(columnDefinition = "varchar(64) default null comment '新增确诊'")
    private String addDiagnosis;
    @Column(columnDefinition = "varchar(64) default null comment '现有确诊'")
    private String nowDiagnosis;
    @Column(columnDefinition = "varchar(64) default null comment '现有确诊较昨日新增'")
    private String nowDiagnosisAdd;
    @Column(columnDefinition = "varchar(64) default null comment '境外输入确诊'")
    private String overseasDiagnosis;
    @Column(columnDefinition = "varchar(64) default null comment '境外输入确诊较昨日新增'")
    private String overseasDiagnosisAdd;
    @Column(columnDefinition = "varchar(64) default null comment '现有疑似'")
    private String nowSuspected;
    @Column(columnDefinition = "varchar(64) default null comment '现有疑似较昨日新增'")
    private String nowSuspectedAdd;


}

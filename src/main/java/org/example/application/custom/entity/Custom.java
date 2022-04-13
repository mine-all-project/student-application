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
    @Column(columnDefinition = "varchar(64) default null comment '全局通知'")
    private String baseNotice;
    @Column(columnDefinition = "tinyint default null comment '体温通知'")
    private String temperatureNotice;
    @ManyToMany
    private List<FileInfo> banner;
}

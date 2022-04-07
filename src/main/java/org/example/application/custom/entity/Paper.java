package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@ToString
public class Paper extends BaseEntity {
    @Column(columnDefinition = "varchar(64) default null comment '标题'")
    private String title;
    @Column(columnDefinition = "longtext default null comment '正文'")
    private String content;
    @Column(columnDefinition = "varchar(64) default null comment '分类'")
    private String type;
    @ManyToOne
    private FileInfo image;
}

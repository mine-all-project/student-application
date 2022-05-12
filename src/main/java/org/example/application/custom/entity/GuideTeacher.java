package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SystemUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@ToString
public class GuideTeacher extends BaseEntity {
    @Column(columnDefinition = "longtext comment '内容'")
    private String content;
    @ManyToOne
    private SystemUser teacher;
}

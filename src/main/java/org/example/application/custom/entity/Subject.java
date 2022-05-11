package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SystemUser;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
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
    @OneToOne
    private SystemUser selecter;
    @ManyToOne
    @CreatedBy
    private SystemUser createBy;
    @ManyToMany
    private List<Status> status;
}

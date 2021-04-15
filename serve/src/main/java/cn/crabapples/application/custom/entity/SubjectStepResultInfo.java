package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 科研项目阶段成果实体类
 */
@Setter
@Getter
@Entity
public class SubjectStepResultInfo extends BaseEntity {
    @Column(columnDefinition = "longtext comment '附件地址'")
    private String url;

    @Column(columnDefinition = "longtext comment '描述'")
    private String content;

    @Column(columnDefinition = "longtext comment '文件名'")
    private String fileName;
}
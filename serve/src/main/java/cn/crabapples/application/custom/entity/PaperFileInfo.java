package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 文章文件实体类
 */
@Setter
@Getter
@Entity
public class PaperFileInfo extends BaseEntity {
    @Column(columnDefinition = "longtext comment '附件地址'")
    private String url;

    @Column(columnDefinition = "longtext comment '文件名'")
    private String name;
}
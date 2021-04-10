package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/7 3:09
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
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
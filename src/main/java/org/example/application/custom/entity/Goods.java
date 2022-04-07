package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Goods extends BaseEntity {
    @Column(columnDefinition = "varchar(64) default null comment '名称'")
    private String name;
    @Column(columnDefinition = "decimal(10,2) default null comment '价格'")
    private BigDecimal price;
    @Column(columnDefinition = "longtext default null comment '详情'")
    private String content;
    @ManyToMany
    private List<FileInfo> images;
}

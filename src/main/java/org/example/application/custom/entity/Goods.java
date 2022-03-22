package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Goods extends BaseEntity {
    private String id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer delFlag;
    private BigDecimal price;
    private String content;
    private String fileInfoId;
}

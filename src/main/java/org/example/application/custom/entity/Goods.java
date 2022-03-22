package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Goods extends BaseEntity {
    private String id;
    private String name;
    private Integer delFlag;
    private BigDecimal price;
    private String content;
    private String fileInfoId;
}

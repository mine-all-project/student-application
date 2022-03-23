package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class Goods extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String content;
    private String fileInfoId;
    private String description;
}

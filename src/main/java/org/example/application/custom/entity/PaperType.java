package org.example.application.custom.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
@Getter
@Setter
public class PaperType extends BaseEntity {
    private String name;
    private String publisherId;
    private BigDecimal price;
    private String typeId;
    private String content;
}

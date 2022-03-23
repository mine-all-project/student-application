package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
public class Order extends BaseEntity {
    private BigDecimal price;
    private String goodsIds;
    private String userId;
    private Integer status;
}

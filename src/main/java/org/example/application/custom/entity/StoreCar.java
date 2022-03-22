package org.example.application.custom.entity;

import lombok.Data;
import org.example.application.common.BaseEntity;
@Data
public class StoreCar extends BaseEntity {
    private String userId;
    private String goodsIds;
}

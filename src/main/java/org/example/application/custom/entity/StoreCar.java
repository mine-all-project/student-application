package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
@Getter
@Setter
public class StoreCar extends BaseEntity {
    private String userId;
    private String goodsIds;
}

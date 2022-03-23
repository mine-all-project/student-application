package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

@Getter
@Setter
public class Favorite extends BaseEntity {
    private String id;

    private String userId;

    private String goodsIds;

    private String paperIds;
}

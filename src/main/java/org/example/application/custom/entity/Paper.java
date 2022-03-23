package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import java.time.LocalDateTime;
@Getter
@Setter
public class Paper extends BaseEntity {
    private String title;
    private String content;
    private String typeId;

}

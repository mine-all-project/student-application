package org.example.application.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 表单基本属性
 */
@Getter
@Setter
public abstract class BaseForm<T extends BaseEntity> {
    protected String id;
    protected int pageIndex;
    protected int pageSize;
    protected LocalDateTime createTime;

    public abstract T toEntity();
}

package org.example.application.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单基本属性
 */
@Getter
@Setter
public abstract class BaseForm<T extends BaseEntity> {
    protected String id;
    protected int pageIndex;
    protected int pageSize;

    public abstract T toEntity();
}

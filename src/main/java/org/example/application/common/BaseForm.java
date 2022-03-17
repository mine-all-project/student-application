package org.example.application.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单基本属性
 */
@Getter
@Setter
public class BaseForm {
    protected String id;
    protected int pageIndex;
    protected int pageSize;
}

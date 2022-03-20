package org.example.application.common;

import org.springframework.data.domain.Sort;

/**
 * 数据库查询基本DTO
 */
public abstract class BaseDAO {
    protected final Sort desByCreateTime = Sort.by("createTime").descending();
    protected final Sort ascByCreateTime = Sort.by("createTime").ascending();
    protected final int IS_DEL = 1;
    protected final int NOT_DEL = 0;

}

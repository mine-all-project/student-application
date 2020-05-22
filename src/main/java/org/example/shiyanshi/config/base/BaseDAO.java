package org.example.shiyanshi.config.base;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseDAO {
    @Value("${database.page-size}")
    protected int pageSize;
    @Value("${database.page-index}")
    protected int pageIndex;
}

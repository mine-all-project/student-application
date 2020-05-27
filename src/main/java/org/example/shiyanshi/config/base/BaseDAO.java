package org.example.shiyanshi.config.base;

import org.example.shiyanshi.entity.LineUps;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public abstract class BaseDAO {
    @Value("${database.page-size}")
    protected int pageSize;
    @Value("${database.page-index}")
    protected int pageIndex;
}

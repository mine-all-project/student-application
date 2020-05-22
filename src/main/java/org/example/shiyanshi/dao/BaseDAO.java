package org.example.shiyanshi.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO {
    @Value("${database.page-size}")
    protected int pageSize;
    @Value("${database.page-index}")
    protected int pageIndex;
}

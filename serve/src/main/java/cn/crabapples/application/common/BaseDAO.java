package cn.crabapples.application.common;

import org.springframework.data.domain.Sort;

/**
 * TODO 请求入参基本DTO
 *
 * @author Mr.He
 * 2019/9/21 17:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class BaseDAO {
    protected final Sort desByCreateTime = Sort.by("createTime").descending();
    protected final Sort ascByCreateTime = Sort.by("createTime").ascending();
    protected final int IS_DEL = 1;
    protected final int NOT_DEL = 0;
}

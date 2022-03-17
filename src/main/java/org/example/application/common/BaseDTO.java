package org.example.application.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果基本DTO
 */
@Getter
@Setter
public abstract class BaseDTO {
    private String userId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

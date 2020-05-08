package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contracts extends BaseEntity{
    private String title;
    private String content;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AudioFiles extends BaseEntity {
    private String url;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

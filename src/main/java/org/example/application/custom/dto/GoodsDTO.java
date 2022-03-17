package org.example.application.custom.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDTO {
    private String id;
    private String username;
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

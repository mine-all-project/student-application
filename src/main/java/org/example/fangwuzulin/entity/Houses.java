package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Houses extends BaseEntity{
    private String address;
    private String user_id;
    private String price;
    private String key_words;
    private String note;
    private String img_src;
    private String contract;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

package org.example.waimai.entity;

import com.alibaba.fastjson.JSONObject;

public class AudioFiles extends BaseEntity {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

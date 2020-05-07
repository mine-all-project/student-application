package org.example.fangwuzulin.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class RandomCode {
    public RandomCode(String code, BufferedImage image) {
        this.code = code;
        this.image = image;
    }

    private String code;
    private BufferedImage image;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
